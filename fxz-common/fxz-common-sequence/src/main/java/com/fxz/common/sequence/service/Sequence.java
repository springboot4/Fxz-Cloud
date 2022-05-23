package com.fxz.common.sequence.service;

import com.fxz.common.sequence.exception.SeqException;

/**
 * 序列号生成器接口
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 09:43
 */
public interface Sequence {

	/**
	 * 生成下一个序列号
	 * @param name 号段名
	 * @return 序列号
	 * @throws SeqException 序列号异常
	 */
	Long next(String name) throws SeqException;

	/**
	 * 下一个生成序号（带格式）
	 * @param name 号段名
	 * @return 序列号
	 * @throws SeqException 序列号异常
	 */
	String nextValue(String name) throws SeqException;

}
