package com.fxz.common.sequence.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 发号器相应参数配置
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 09:37
 */
@Getter
@Setter
@ConfigurationProperties("fxz.common.sequence")
public class SequenceProperties {

	/**
	 * 存储类型
	 */
	private Type type = Type.MYSQL;

	/**
	 * 序列生成器默认前缀
	 */
	private String keyPrefix = "sequence:";

	/**
	 * 序列生成器默认步长
	 */
	private Integer step = 1;

	/**
	 * 序列生成器默认号段步长
	 */
	private Integer rangeStep = 1000;

	/**
	 * 序列生成器默认号段起始位置
	 */
	private Long rangeStart = 0L;

	/**
	 * 存储类型
	 */
	public enum Type {

		/**
		 * redis
		 */
		REDIS,

		/**
		 * mysql
		 */
		MYSQL;

	}

}
