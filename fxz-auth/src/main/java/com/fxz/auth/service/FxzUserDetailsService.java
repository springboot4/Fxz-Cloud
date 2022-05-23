package com.fxz.auth.service;

import com.fxz.common.security.entity.FxzAuthUser;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author fxz
 */
public interface FxzUserDetailsService extends UserDetailsService, Ordered {

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	default boolean support(String clientId, String grantType) {
		return true;
	}

	/**
	 * 排序值 默认取最大的
	 * @return 排序值
	 */
	default int getOrder() {
		return 0;
	}

	/**
	 * 通过用户实体查询
	 * @param fxzAuthUser user
	 */
	default UserDetails loadUserByUser(FxzAuthUser fxzAuthUser) {
		return this.loadUserByUsername(fxzAuthUser.getUsername());
	}

}
