package com.fxz.common.redis.cache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fxz
 */
@Data
@ConfigurationProperties(prefix = "spring.cache.multi")
public class CacheRedisCaffeineProperties {

	private Set<String> cacheNames = new HashSet<>();

	/**
	 * 是否存储空值，默认true，防止缓存穿透
	 */
	private boolean cacheNullValues = true;

	/**
	 * 是否动态根据cacheName创建Cache的实现，默认true
	 */
	private boolean dynamic = true;

	/**
	 * 缓存key的前缀
	 */
	private String cachePrefix;

	@NestedConfigurationProperty
	private RedisProperties redis = new RedisProperties();

	@NestedConfigurationProperty
	private CaffeineProperties caffeine = new CaffeineProperties();

}
