package com.fxz.common.sequence.segment;

import com.fxz.common.sequence.exception.SeqException;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 09:46
 */
public interface SeqSegmentManager {

	/**
	 * 获取指定号段名的下一个号段
	 * @param name 号段名
	 * @param seqSegmentConfig 序列号号段配置
	 * @return 返回号段
	 * @throws SeqException 序列号异常
	 */
	SeqSegment nextSegment(String name, SeqSegmentConfig seqSegmentConfig) throws SeqException;

}
