package com.fxz.common.sequence.segment;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 序列号号段配置
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 09:48
 */
@Data
@Accessors(chain = true)
public class SeqSegmentConfig {

	/**
	 * 序列生成器步长
	 */
	private Integer step = 1;

	/**
	 * 序列生成器号段步长
	 */
	private Integer rangeStep = 1000;

	/**
	 * 序列生成器号段起始位置
	 */
	private Long rangeStart = 0L;

}
