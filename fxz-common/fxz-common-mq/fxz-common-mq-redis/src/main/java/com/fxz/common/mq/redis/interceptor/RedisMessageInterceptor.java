package com.fxz.common.mq.redis.interceptor;

import com.fxz.common.mq.redis.message.AbstractRedisMessage;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 16:12
 */
public interface RedisMessageInterceptor {

	default void sendMessageBefore(AbstractRedisMessage message) {
	}

	default void sendMessageAfter(AbstractRedisMessage message) {
	}

	default void consumeMessageBefore(AbstractRedisMessage message) {
	}

	default void consumeMessageAfter(AbstractRedisMessage message) {
	}

}
