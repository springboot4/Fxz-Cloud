package com.fxz.common.sequence.segment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 序列号号段对象
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 09:47
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class SeqSegment {

	/**
	 * 号段的序列号开始值
	 */
	private final Long min;

	/**
	 * 号段的序列号结束值
	 */
	private final Long max;

	/**
	 * 号段的序列号当前值
	 */
	private final AtomicLong value;

	/**
	 * 号段的序列号步长
	 */
	private final Integer step;

	/**
	 * 号段的序列号是否分配完毕，每次分配完毕就会去重新获取一个新的号段
	 */
	private volatile Boolean over = Boolean.FALSE;

	public SeqSegment(Long min, Long max, Integer step) {
		this.min = min;
		this.max = max;
		this.step = step;
		this.value = new AtomicLong(min);
	}

	/**
	 * 返回并递增下一个序列号
	 * @return 下一个序列号，如果返回-1表示序列号分配完毕
	 */
	public Long getAndIncrement() {
		Long currentValue = value.getAndIncrement();
		if (currentValue > max) {
			over = Boolean.TRUE;
			return -1L;
		}
		return currentValue;
	}

}
