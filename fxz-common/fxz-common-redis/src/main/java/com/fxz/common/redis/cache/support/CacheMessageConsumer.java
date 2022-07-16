package com.fxz.common.redis.cache.support;

import com.fxz.common.mq.redis.stream.AbstractStreamMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fxz
 */
@Slf4j
@RequiredArgsConstructor
public class CacheMessageConsumer extends AbstractStreamMessageListener<CacheMessage> {

	private final RedisCaffeineCacheManager redisCaffeineCacheManager;

	@Override
	public void onMessage(CacheMessage cacheMessage) {
		log.info("接收到redis stream消息，清空本地缓存，缓存名称:{}, key:{}", cacheMessage.getCacheName(), cacheMessage.getKey());
		redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey());
	}

}