package com.fxz.common.redis.cache.support;

import com.fxz.common.mq.redis.core.RedisMQTemplate;
import com.fxz.common.redis.cache.properties.CacheRedisCaffeineProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author fxz
 */
@Slf4j
public class RedisCaffeineCacheManager implements CacheManager {

	private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

	private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

	private RedisTemplate<String, Object> stringKeyRedisTemplate;

	private RedisMQTemplate redisMQTemplate;

	private boolean dynamic;

	private Set<String> cacheNames;

	public RedisCaffeineCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties,
			RedisTemplate redisTemplate, RedisMQTemplate redisMQTemplate) {
		this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties;
		this.stringKeyRedisTemplate = redisTemplate;
		this.redisMQTemplate = redisMQTemplate;
		this.dynamic = cacheRedisCaffeineProperties.isDynamic();
		this.cacheNames = cacheRedisCaffeineProperties.getCacheNames();
	}

	@Override
	public Cache getCache(String name) {
		Cache cache = cacheMap.get(name);
		if (cache != null) {
			return cache;
		}
		if (!dynamic && !cacheNames.contains(name)) {
			return cache;
		}

		cache = new RedisCaffeineCache(name, redisMQTemplate, stringKeyRedisTemplate, caffeineCache(),
				cacheRedisCaffeineProperties);
		Cache oldCache = cacheMap.putIfAbsent(name, cache);
		log.debug("create cache instance, the cache name is : {}", name);
		return oldCache == null ? cache : oldCache;
	}

	public com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeineCache() {
		Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();

		if (cacheRedisCaffeineProperties.getCaffeine().getExpireAfterAccess() > 0) {
			cacheBuilder.expireAfterAccess(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterAccess(),
					TimeUnit.MILLISECONDS);
		}
		if (cacheRedisCaffeineProperties.getCaffeine().getExpireAfterWrite() > 0) {
			cacheBuilder.expireAfterWrite(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterWrite(),
					TimeUnit.MILLISECONDS);
		}
		if (cacheRedisCaffeineProperties.getCaffeine().getInitialCapacity() > 0) {
			cacheBuilder.initialCapacity(cacheRedisCaffeineProperties.getCaffeine().getInitialCapacity());
		}
		if (cacheRedisCaffeineProperties.getCaffeine().getMaximumSize() > 0) {
			cacheBuilder.maximumSize(cacheRedisCaffeineProperties.getCaffeine().getMaximumSize());
		}
		if (cacheRedisCaffeineProperties.getCaffeine().getRefreshAfterWrite() > 0) {
			cacheBuilder.refreshAfterWrite(cacheRedisCaffeineProperties.getCaffeine().getRefreshAfterWrite(),
					TimeUnit.MILLISECONDS);
		}

		return cacheBuilder.build();
	}

	@Override
	public Collection<String> getCacheNames() {
		return this.cacheNames;
	}

	/**
	 * todo redis key过期清理本地缓存
	 */
	public void clearLocal(String cacheName, Object key) {
		Cache cache = null;

		if (StringUtils.hasText(cacheName)) {
			cache = cacheMap.get(cacheName);
		}

		if (Objects.isNull(cache)) {
			return;
		}

		RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache) cache;
		redisCaffeineCache.clearLocal(key);
	}

}
