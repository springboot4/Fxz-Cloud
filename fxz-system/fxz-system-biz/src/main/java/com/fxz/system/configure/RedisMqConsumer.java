package com.fxz.system.configure;

import com.fxz.common.mq.redis.stream.AbstractStreamMessageListener;
import com.fxz.system.entity.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试redis stream相关功能
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 18:37
 */
@Slf4j
@Component
public class RedisMqConsumer extends AbstractStreamMessageListener<TestMessage> {

	@Override
	public void onMessage(TestMessage message) {
		log.info("消费者接收到消息:{}", message);
	}

}
