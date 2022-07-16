package com.fxz.common.redis.config;

import com.fxz.common.mq.redis.core.RedisMQTemplate;
import com.fxz.common.redis.cache.properties.CacheRedisCaffeineProperties;
import com.fxz.common.redis.cache.support.CacheMessageConsumer;
import com.fxz.common.redis.cache.support.RedisCaffeineCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author fxz
 */
@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
public class CacheRedisCaffeineAutoConfiguration {

	@Autowired
	private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

	@Bean
	public RedisCaffeineCacheManager cacheManager(RedisTemplate<String, Object> redisTemplate,
			RedisMQTemplate redisMQTemplate) {
		return new RedisCaffeineCacheManager(cacheRedisCaffeineProperties, redisTemplate, redisMQTemplate);
	}

	@Bean
	public CacheMessageConsumer cacheMessageConsumer(RedisCaffeineCacheManager redisCaffeineCacheManager) {
		return new CacheMessageConsumer(redisCaffeineCacheManager);
	}

}
