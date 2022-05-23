package com.fxz.common.redis.config;

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
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-25 13:48 默认的@Cacheable注解不支持配置过期时间，默认永不过期
 * 通过{@link CachingProperties}可以在这里的redisCacheConfigurationMap中添加key的过期配置
 */
@AllArgsConstructor
@EnableConfigurationProperties(CachingProperties.class)
@EnableCaching
@AutoConfiguration
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisCachingConfig extends CachingConfigurerSupport {

	private final CachingProperties cachingProperties;

	/**
	 * 不配置key的情况,将方法名作为缓存key名称
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> method.getName();
	}

	/**
	 * 缓存管理器
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		return new RedisCacheManager(
				// Redis 缓存写入器
				RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
				// 默认过期策略，未配置的 key 会使用这个
				this.getRedisCacheConfigurationWithTtl(cachingProperties.getDefaultTtl()),
				// 指定 key 策略
				this.getRedisCacheConfigurationMap());
	}

	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		// 进行过期时间配置 key , ttl
		Set<String> keySet = cachingProperties.getKeysTtl().keySet();
		if (!CollectionUtils.isEmpty(keySet)) {
			keySet.forEach(key -> redisCacheConfigurationMap.put(key,
					this.getRedisCacheConfigurationWithTtl(cachingProperties.getKeysTtl().get(key))));
		}
		return redisCacheConfigurationMap;
	}

	/**
	 * 缓存管理器策略过期时间配置
	 */
	private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
		// 序列化方式
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();

		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

		om.registerModule(createJavaTimeModule());

		// ObjectMapper.DefaultTyping.NON_FINAL指定序列化输入的类型
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.WRAPPER_ARRAY);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration = redisCacheConfiguration
				// 设置key为String
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
				// 设置value 为自动转Json的Object
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				// 设置过期时间
				.entryTtl(Duration.ofSeconds(seconds))
				// 覆盖默认的构造key，否则会多出一个冒号
				.computePrefixWith(name -> name + ":");

		return redisCacheConfiguration;
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

}
