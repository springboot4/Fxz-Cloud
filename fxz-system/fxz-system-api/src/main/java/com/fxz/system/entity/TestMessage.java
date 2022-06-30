package com.fxz.system.entity;

import com.fxz.common.mq.redis.stream.AbstractStreamMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 测试redis stream相关功能
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 18:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestMessage extends AbstractStreamMessage {

	Long id;

	Integer age;

	String message;

	List<String> list;

	@Override
	public String getStreamKey() {
		return "fxz.test.stream";
	}

}
