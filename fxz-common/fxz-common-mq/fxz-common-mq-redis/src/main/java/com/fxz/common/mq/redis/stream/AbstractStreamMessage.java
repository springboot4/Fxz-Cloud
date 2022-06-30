package com.fxz.common.mq.redis.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fxz.common.mq.redis.message.AbstractRedisMessage;

/**
 * Redis Stream Message 抽象类
 *
 * @author fxz
 */
public abstract class AbstractStreamMessage extends AbstractRedisMessage {

	/**
	 * 获得 Redis Stream Key
	 * @return Channel
	 * @JsonIgnore 避免序列化。原因是，Redis 发布 Channel 消息的时候，已经会指定。
	 */
	@JsonIgnore
	public abstract String getStreamKey();

}
