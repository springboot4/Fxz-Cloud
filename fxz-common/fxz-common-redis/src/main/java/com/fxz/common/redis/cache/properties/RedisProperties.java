package com.fxz.common.redis.cache.properties;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fxz
 */
@Data
public class RedisProperties {

	/**
	 * 全局过期时间，单位毫秒，默认不过期
	 */
	private long defaultExpiration = 0;

	/**
	 * 每个cacheName的过期时间，单位毫秒，优先级比defaultExpiration高
	 */
	private Map<String, Long> expires = new HashMap<>();

	/**
	 * 缓存更新时通知其他节点的topic名称
	 */
	private String topic = "cache:redis:caffeine:topic";

}