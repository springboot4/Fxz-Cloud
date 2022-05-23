package com.fxz.common.core.constant;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 13:10
 */
public interface FxzConstant {

	/**
	 * GATEWAY请求头TOKEN名称（不要有空格）
	 */
	public static final String GATEWAY_TOKEN_HEADER = "GatewayToken";

	/**
	 * GATEWAY请求头TOKEN值
	 */
	public static final String GATEWAY_TOKEN_VALUE = "fxz:gateway:$**$";

	/**
	 * gif类型
	 */
	public static final String GIF = "gif";

	/**
	 * png类型
	 */
	public static final String PNG = "png";

	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * GBK 字符集
	 */
	public static final String GBK = "GBK";

	/**
	 * RMI 远程方法调用
	 */
	public static final String LOOKUP_RMI = "rmi:";

	/**
	 * LDAP 远程方法调用
	 */
	public static final String LOOKUP_LDAP = "ldap:";

	/**
	 * LDAPS 远程方法调用
	 */
	public static final String LOOKUP_LDAPS = "ldaps:";

	/**
	 * http请求
	 */
	public static final String HTTP = "http://";

	/**
	 * https请求
	 */
	public static final String HTTPS = "https://";

	/**
	 * 验证码 key前缀
	 */
	public static final String CODE_PREFIX = "fxz.captcha.";

	public static final String OAUTH2_TOKEN_TYPE = "Bearer ";

	public static final String OAUTH2_TOKEN_TYPE_LOW = "bearer";

	public static final Integer STATUS_YES = 1;

}
