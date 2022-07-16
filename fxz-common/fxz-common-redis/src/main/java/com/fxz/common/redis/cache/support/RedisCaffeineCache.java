package com.fxz.common.redis.cache.support;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fxz.common.mq.redis.core.RedisMQTemplate;
import com.fxz.common.redis.cache.properties.CacheRedisCaffeineProperties;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fxz
 */
@SuppressWarnings("all")
@Slf4j
public class RedisCaffeineCache extends AbstractValueAdaptingCache {

	private final Logger logger = LoggerFactory.getLogger(RedisCaffeineCache.class);

	private String name;

	private RedisMQTemplate redisMQTemplate;

	private RedisTemplate<String, Object> stringKeyRedisTemplate;

	private Cache<Object, Object> caffeineCache;

	private String cachePrefix;

	private long defaultExpiration = 0;

	private Map<String, Long> expires;

	private String topic = "cache:redis:caffeine:topic";

	private Map<String, ReentrantLock> keyLockMap = new ConcurrentHashMap<String, ReentrantLock>();

	public RedisCaffeineCache(String name, RedisMQTemplate redisMQTemplate,
			RedisTemplate<String, Object> stringKeyRedisTemplate, Cache<Object, Object> caffeineCache,
			CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
		super(cacheRedisCaffeineProperties.isCacheNullValues());
		this.name = name;
		this.redisMQTemplate = redisMQTemplate;
		this.stringKeyRedisTemplate = buildRedisTemplate(stringKeyRedisTemplate);
		this.caffeineCache = caffeineCache;
		this.cachePrefix = cacheRedisCaffeineProperties.getCachePrefix();
		this.defaultExpiration = cacheRedisCaffeineProperties.getRedis().getDefaultExpiration();
		this.expires = cacheRedisCaffeineProperties.getRedis().getExpires();
		this.topic = cacheRedisCaffeineProperties.getRedis().getTopic();
	}

	public RedisTemplate buildRedisTemplate(RedisTemplate redisTemplate) {
		// 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.registerModule(createJavaTimeModule());
		// ObjectMapper.DefaultTyping.NON_FINAL指定序列化输入的类型
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.WRAPPER_ARRAY);

		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
		redisTemplate.setValueSerializer(serializer);
		redisTemplate.setHashValueSerializer(serializer);

		redisTemplate.setKeySerializer(new StringRedisSerializer());

		return redisTemplate;
	}

	private JavaTimeModule createJavaTimeModule() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();

		// yyyy-MM-dd HH:mm:ss
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));
		// yyyy-MM-dd
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
		// HH:mm:ss
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
		// Instant 类型序列化
		javaTimeModule.addSerializer(Instant.class, InstantSerializer.INSTANCE);

		// yyyy-MM-dd HH:mm:ss
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));
		// yyyy-MM-dd
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
		// HH:mm:ss
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
		// Instant 反序列化
		javaTimeModule.addDeserializer(Instant.class, InstantDeserializer.INSTANT);

		return javaTimeModule;
	}

	/**
	 * cacheName，缓存的名字，默认实现中一般是CacheManager创建Cache的bean时传入cacheName
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * 获取实际使用的缓存，如：RedisTemplate、com.github.benmanes.caffeine.cache.Cache<Object,
	 * Object>。暂时没发现实际用处，可能只是提供获取原生缓存的bean，以便需要扩展一些缓存操作或统计之类的东西
	 */
	@Override
	public Object getNativeCache() {
		return this;
	}

	/**
	 * 通过key获取缓存值，可以使用valueLoader.call()来调使用@Cacheable注解的方法。
	 * 当@Cacheable注解的sync属性配置为true时使用此方法。因此方法内需要保证回源到数据库的同步性。避免在缓存失效时大量请求回源到数据库
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Object value = lookup(key);
		if (value != null) {
			return (T) value;
		}

		ReentrantLock lock = keyLockMap.computeIfAbsent(key.toString(), s -> {
			log.trace("create lock for key : {}", s);
			return new ReentrantLock();
		});

		lock.lock();
		try {
			value = lookup(key);
			if (value != null) {
				return (T) value;
			}
			value = valueLoader.call();
			log.info("valueLoader.call()执行...");
			Object storeValue = toStoreValue(value);
			put(key, storeValue);
			return (T) value;
		}
		catch (Exception e) {
			throw new ValueRetrievalException(key, valueLoader, e.getCause());
		}
		finally {
			lock.unlock();
		}
	}

	/**
	 * 将@Cacheable注解方法返回的数据放入缓存中
	 * @param key the key with which the specified value is to be associated
	 * @param value the value to be associated with the specified key
	 */
	@Override
	public void put(Object key, Object value) {
		if (!super.isAllowNullValues() && value == null) {
			this.evict(key);
			return;
		}

		doPut(key, value);
	}

	/**
	 * 当缓存中不存在key时才放入缓存。返回值是当key存在时原有的数据
	 * @param key the key with which the specified value is to be associated
	 * @param value the value to be associated with the specified key
	 */
	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object cacheKey = getKey(key.toString());
		Object prevValue;
		// 考虑使用分布式锁，或者将redis的setIfAbsent改为原子性操作
		synchronized (key) {
			prevValue = stringKeyRedisTemplate.opsForValue().get(cacheKey);
			if (prevValue == null) {
				doPut(key, value);
			}
		}
		return toValueWrapper(prevValue);
	}

	private void doPut(Object key, Object value) {
		long expire = getExpire();

		if (expire > 0) {
			stringKeyRedisTemplate.opsForValue().set(getKey(key.toString()), toStoreValue(value), expire,
					TimeUnit.MILLISECONDS);
		}
		else {
			Object o = toStoreValue(value);
			stringKeyRedisTemplate.opsForValue().set(getKey(key.toString()), o);
		}

		push(new CacheMessage(this.name, key));

		caffeineCache.put(key, toStoreValue(value));
	}

	/**
	 * 删除缓存
	 * @param key the key whose mapping is to be removed from the cache
	 */
	@Override
	public void evict(Object key) {
		// 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
		stringKeyRedisTemplate.delete(getKey(key.toString()));

		push(new CacheMessage(this.name, key));

		caffeineCache.invalidate(key);
	}

	/**
	 * 删除缓存中的所有数据。需要注意的是，具体实现中只删除使用@Cacheable注解缓存的所有数据，不要影响应用内的其他缓存
	 */
	@Override
	public void clear() {
		// 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
		Set<String> keys = stringKeyRedisTemplate.keys(this.name.concat(":*"));
		for (String key : keys) {
			stringKeyRedisTemplate.delete(key);
		}

		push(new CacheMessage(this.name, null));

		caffeineCache.invalidateAll();
	}

	@Override
	protected Object lookup(Object key) {
		Object cacheKey = getKey(key.toString());
		Object value = caffeineCache.getIfPresent(key);
		if (value != null) {
			logger.info("从本地缓存caffeine中获取缓存，key: {}", cacheKey);
			return value;
		}

		value = stringKeyRedisTemplate.opsForValue().get(cacheKey);

		if (value != null) {
			logger.info("从redis中获取缓存, key : {}", cacheKey);
			caffeineCache.put(key, value);
		}
		return value;
	}

	private String getKey(String key) {
		return this.name.concat(":")
				.concat(!StringUtils.hasText(cachePrefix) ? key : cachePrefix.concat(":").concat(key));
	}

	private long getExpire() {
		long expire = defaultExpiration;
		Long cacheNameExpire = expires.get(this.name);
		return cacheNameExpire == null ? expire : cacheNameExpire;
	}

	/**
	 * @param message
	 * @description 缓存变更时通知其他节点清理本地缓存
	 */
	private void push(CacheMessage message) {
		redisMQTemplate.send(message);
	}

	/**
	 * @param key
	 * @description 清理本地缓存
	 */
	public void clearLocal(Object key) {
		logger.info("清理本地缓存 : {}", key);
		if (key == null) {
			caffeineCache.invalidateAll();
		}
		else {
			caffeineCache.invalidate(key);
		}
	}

}
