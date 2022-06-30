package com.fxz.common.mq.redis.message;

import java.util.HashMap;
import java.util.Map;

/**
 * redis 消息抽象基类
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 16:16
 */
public abstract class AbstractRedisMessage {

	private Map<String, String> headers = new HashMap<>();

	public String getHeader(String key) {
		return headers.get(key);
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

}
