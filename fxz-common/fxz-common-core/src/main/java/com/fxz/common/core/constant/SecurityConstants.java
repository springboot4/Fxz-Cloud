
package com.fxz.common.core.constant;

/**
 * @author fxz
 */
public interface SecurityConstants {

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 短信验证码key前缀
	 */
	String SMS_CODE_PREFIX = "SMS_CODE:";

	/**
	 * 刷新token
	 */
	String REFRESH_TOKEN = "refresh_token";

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";

	/**
	 * 前缀
	 */
	String FXZ_PREFIX = "fxz_";

	/**
	 * token 相关前缀
	 */
	String TOKEN_PREFIX = "fxz_cloud:oauth:";

	/**
	 * oauth 相关前缀
	 */
	String OAUTH_PREFIX = "oauth:";

	/**
	 * 系统管理 web 客户端ID
	 */
	String ADMIN_CLIENT_ID = "fxz";

	/**
	 * 移动端（H5/Android/IOS）客户端ID
	 */
	String APP_CLIENT_ID = "mall-app";

	/**
	 * 小程序端（微信小程序、....） 客户端ID
	 */
	String WEAPP_CLIENT_ID = "mall-weapp";

	/**
	 * 授权码模式code key 前缀
	 */
	String OAUTH_CODE_PREFIX = "oauth:code:";

	/**
	 * OAUTH URL
	 */
	String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * 客户端模式
	 */
	String CLIENT_CREDENTIALS = "client_credentials";

	/**
	 * 客户端编号
	 */
	String CLIENT_ID = "client_id";

	/**
	 * 客户端编号
	 */
	String AUTH_TYPE = "auth_type";

	/**
	 * 用户ID字段
	 */
	String DETAILS_USER_ID = "id";

	/**
	 * 用户名
	 */
	String DETAILS_USERNAME = "username";

	/**
	 * 用户基本信息
	 */
	String DETAILS_USER = "user_info";

	/**
	 * 用户名phone
	 */
	String DETAILS_PHONE = "phone";

	/**
	 * 头像
	 */
	String DETAILS_AVATAR = "avatar";

	/**
	 * 昵称
	 */
	String NICKNAME = "nickname";

	/**
	 * 姓名
	 */
	String NAME = "name";

	/**
	 * 姓名
	 */
	String PASSWORD = "password";

	/**
	 * 邮箱
	 */
	String EMAIL = "email";

	/**
	 * 用户部门字段
	 */
	String DETAILS_DEPT_ID = "deptId";

	/**
	 * 租户ID 字段
	 */
	String DETAILS_TENANT_ID = "tenantId";

	/**
	 * 协议字段
	 */
	String DETAILS_LICENSE = "license";

	/**
	 * 激活字段 兼容外围系统接入
	 */
	String ACTIVE = "active";

	/**
	 * AES 加密
	 */
	String AES = "aes";

	/**
	 * 认证请求头key
	 */
	String AUTHORIZATION_KEY = "Authorization";

	/**
	 * Basic认证前缀
	 */
	String BASIC_PREFIX = "Basic ";

	/**
	 * Basic认证前缀
	 */
	String BASIC_PREFIX_LOW = "basic ";

	/**
	 * sys_oauth_client_details 表的字段，不包括client_id、client_secret
	 */
	String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove";

	/**
	 * JdbcClientDetailsService 查询语句
	 */
	String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

	/**
	 * 按条件client_id 查询
	 */
	String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

	/**
	 * 默认的查询语句
	 */
	String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

}
