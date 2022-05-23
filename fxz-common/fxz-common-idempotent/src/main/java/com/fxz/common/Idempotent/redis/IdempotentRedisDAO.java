package com.fxz.common.Idempotent.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 格式化key 对redis进行操作
 *
 * @author fxz
 */
@SuppressWarnings("all")
@AllArgsConstructor
public class IdempotentRedisDAO {

	private static final String REDISTEMPLATE = "idempotent:%s";

	private final StringRedisTemplate redisTemplate;

	public Boolean setIfAbsent(String key, long timeout, TimeUnit timeUnit) {
		String redisKey = formatKey(key);
		return redisTemplate.opsForValue().setIfAbsent(redisKey, "", timeout, timeUnit);
	}

	private static String formatKey(String key) {
		return String.format(REDISTEMPLATE, key);
	}

}
