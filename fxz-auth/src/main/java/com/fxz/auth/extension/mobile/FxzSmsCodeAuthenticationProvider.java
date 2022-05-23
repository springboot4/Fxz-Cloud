package com.fxz.auth.extension.mobile;

import cn.hutool.core.util.StrUtil;
import com.fxz.auth.service.member.FxzMemberUserDetailsServiceImpl;
import com.fxz.auth.service.user.FxzUserDetailServiceImpl;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.security.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;

/**
 * 短信验证码认证授权提供者
 *
 * @author fxz
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FxzSmsCodeAuthenticationProvider implements AuthenticationProvider {

	private StringRedisTemplate redisTemplate;

	private FxzMemberUserDetailsServiceImpl fxzMemberUserDetailsService;

	private FxzUserDetailServiceImpl fxzUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		FxzSmsCodeAuthenticationToken authenticationToken = (FxzSmsCodeAuthenticationToken) authentication;
		String mobile = (String) authenticationToken.getPrincipal();
		String code = (String) authenticationToken.getCredentials();

		String codeKey = SecurityConstants.SMS_CODE_PREFIX + mobile;
		String correctCode = redisTemplate.opsForValue().get(codeKey);
		// 验证码比对
		if (StrUtil.isBlank(correctCode) || !code.equals(correctCode)) {
			throw new FxzException("验证码不正确");
		}
		// 比对成功删除缓存的验证码
		redisTemplate.delete(codeKey);

		UserDetails userDetails;
		String authType = SecurityUtil.getAuthType();
		if (authType == null) {
			// 查询会员用户信息
			userDetails = fxzMemberUserDetailsService.loadUserByMobile(mobile);
		}
		else {
			// 查询系统用户信息
			userDetails = fxzUserDetailService.loadUserByMobile(mobile);
		}

		authenticationToken = new FxzSmsCodeAuthenticationToken(userDetails, authentication.getCredentials(),
				new HashSet<>());
		authenticationToken.setDetails(authentication.getDetails());

		return authenticationToken;
	}

	/**
	 * 是否匹配此Provider
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return FxzSmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
