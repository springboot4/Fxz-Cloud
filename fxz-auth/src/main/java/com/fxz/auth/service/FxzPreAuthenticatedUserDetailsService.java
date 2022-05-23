package com.fxz.auth.service;

import com.fxz.auth.service.member.FxzMemberUserDetailsServiceImpl;
import com.fxz.auth.service.user.FxzUserDetailServiceImpl;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.util.SecurityUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 认证前，根据客户端id选择UserDetailsService
 *
 * @author fxz
 */
@Slf4j
@SuppressWarnings("all")
@NoArgsConstructor
public class FxzPreAuthenticatedUserDetailsService<T extends Authentication>
		implements AuthenticationUserDetailsService<T>, InitializingBean {

	/**
	 * 客户端ID和用户服务 UserDetailService 的映射
	 */
	private Map<String, UserDetailsService> userDetailsServiceMap;

	public FxzPreAuthenticatedUserDetailsService(Map<String, UserDetailsService> userDetailsServiceMap) {
		Assert.notNull(userDetailsServiceMap, "userDetailsService cannot be null.");
		this.userDetailsServiceMap = userDetailsServiceMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsServiceMap, "UserDetailsService must be set");
	}

	/**
	 * 重写PreAuthenticatedAuthenticationProvider 的 preAuthenticatedUserDetailsService 属性，
	 * 可根据客户端和认证方式选择用户服务 UserDetailService 获取用户信息 UserDetail
	 * @param authentication
	 * @return userDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
		// 获取客户端id
		String clientId = SecurityUtil.getOAuth2ClientId();

		log.info("clientId:{}", clientId);

		// 根据客户端id获取获取UserDetailsService
		UserDetailsService userDetailsService = userDetailsServiceMap.get(clientId);

		// 根据客户端选择加载用户的方式
		if (clientId.equals(SecurityConstants.APP_CLIENT_ID)) {
			log.info("移动端");
			return ((FxzMemberUserDetailsServiceImpl) userDetailsService).loadUserByMobile(authentication.getName());
		}
		else if (clientId.equals(SecurityConstants.WEAPP_CLIENT_ID)) {
			log.info("小程序端");
			// 小程序的用户体系是会员，认证方式是通过微信三方标识 openid 认证
			return ((FxzMemberUserDetailsServiceImpl) userDetailsService).loadUserByOpenId(authentication.getName());
		}
		else if (clientId.equals(SecurityConstants.ADMIN_CLIENT_ID)) {
			log.info("系统管理端");
			String authType = SecurityUtil.getAuthType();
			if (authType == null) {
				// 系统用户，认证方式通过用户名 username 认证
				return ((FxzUserDetailServiceImpl) userDetailsService).loadUserByUsername(authentication.getName());
			}
			else {
				// 系统用户，认证方式通过用户名 手机号 认证
				return ((FxzUserDetailServiceImpl) userDetailsService).loadUserByMobile(authentication.getName());
			}
		}
		else {
			return ((FxzUserDetailServiceImpl) userDetailsService).loadUserByUsername(authentication.getName());
		}
	}

}
