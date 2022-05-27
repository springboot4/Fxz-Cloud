package com.fxz.common.canal.common;

/**
 * @author fxz
 */
public interface NamingPolicy {

	/**
	 * 属性名 -> 列名命名转换
	 */
	String convert(String source);

}
