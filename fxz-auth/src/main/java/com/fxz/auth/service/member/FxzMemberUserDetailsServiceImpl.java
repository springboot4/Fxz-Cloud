package com.fxz.auth.service.member;

import com.fxz.auth.service.FxzUserDetailsService;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.mall.user.entity.Member;
import com.fxz.mall.user.feign.RemoteMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商城会员用户认证服务
 *
 * @author fxz
 */
@Service("fxzMemberUserDetailsServiceImpl")
@RequiredArgsConstructor
public class FxzMemberUserDetailsServiceImpl implements FxzUserDetailsService {

	private final RemoteMemberService memberFeignClient;

	/**
	 * 手机号码认证方式
	 * @param mobile 手机号
	 * @return UserDetails
	 */
	public UserDetails loadUserByMobile(String mobile) {
		Member systemUser = memberFeignClient.loadUserByMobile(mobile, SecurityConstants.FROM_IN).getData();

		if (Objects.nonNull(systemUser)) {
			FxzAuthUser authUser = new FxzAuthUser(systemUser.getNickName(), systemUser.getPassword(), true, true, true,
					true, AuthorityUtils.commaSeparatedStringToAuthorityList(null));

			BeanUtils.copyProperties(systemUser, authUser);
			authUser.setUserId(systemUser.getId());

			return authUser;
		}

		throw new UsernameNotFoundException("用户不存在");
	}

	/**
	 * openid 认证方式
	 * @param openId 微信openId
	 * @return UserDetails
	 */
	public UserDetails loadUserByOpenId(String openId) {
		Member systemUser = memberFeignClient.loadUserByOpenId(openId, SecurityConstants.FROM_IN).getData();

		if (Objects.nonNull(systemUser)) {
			FxzAuthUser authUser = new FxzAuthUser(systemUser.getNickName(), systemUser.getOpenid(), true, true, true,
					true, AuthorityUtils.commaSeparatedStringToAuthorityList(null));
			BeanUtils.copyProperties(systemUser, authUser);
			authUser.setUserId(systemUser.getId());

			return authUser;
		}

		throw new UsernameNotFoundException("用户不存在");
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}

}
