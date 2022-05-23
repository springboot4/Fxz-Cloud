package com.fxz.auth.service.user;

import cn.hutool.core.util.ObjectUtil;
import com.fxz.auth.manager.FxzUserManager;
import com.fxz.auth.service.FxzUserDetailsService;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.system.entity.SystemUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-27 16:18
 */
@Slf4j
@Service("fxzUserDetailServiceImpl")
@RequiredArgsConstructor
public class FxzUserDetailServiceImpl implements FxzUserDetailsService {

	private final FxzUserManager fxzUserManager;

	/**
	 * 通过用户名从数据库中获取用户信息SystemUser和用户权限集合
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String clientId = SecurityUtil.getOAuth2ClientId();
		log.info("clientId:{}", clientId);

		if (clientId.equals(SecurityConstants.ADMIN_CLIENT_ID)) {
			log.info("系统端接口");
			SystemUser systemUser = fxzUserManager.findByName(username);
			if (ObjectUtil.isNotEmpty(systemUser)) {
				String permissions = fxzUserManager.findUserPermissions(systemUser.getUsername());
				boolean notLocked = false;
				if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
					notLocked = true;
				FxzAuthUser authUser = new FxzAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true,
						true, notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

				BeanUtils.copyProperties(systemUser, authUser);
				return authUser;
			}

		}
		return null;
	}

	/**
	 * 手机号码认证方式
	 * @param mobile 手机号
	 * @return UserDetails
	 */
	public UserDetails loadUserByMobile(String mobile) {
		SystemUser systemUser = fxzUserManager.findByMobile(mobile);
		if (ObjectUtil.isNotEmpty(systemUser)) {
			String permissions = fxzUserManager.findUserPermissions(systemUser.getUsername());
			boolean notLocked = false;
			if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
				notLocked = true;
			FxzAuthUser authUser = new FxzAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true,
					notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

			BeanUtils.copyProperties(systemUser, authUser);
			return authUser;
		}
		return null;
	}

}
