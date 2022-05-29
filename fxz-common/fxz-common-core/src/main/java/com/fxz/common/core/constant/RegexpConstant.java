package com.fxz.common.core.constant;

/**
 * @author fxz
 */
public interface RegexpConstant {

	/**
	 * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
	 */
	public static final String MOBILE_REG = "[1]\\d{10}";

	/**
	 * http协议正则
	 */
	public static final String HTTP_PROTOCOL_REGEXP = "^((http[s]{0,1})://)";


}