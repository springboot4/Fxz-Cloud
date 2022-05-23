package com.fxz.auth.extension.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 手机验证码授权者
 *
 * @author fxz
 */
@SuppressWarnings("all")
public class FxzSmsCodeTokenGranter extends AbstractTokenGranter {

	/**
	 * 声明授权者 SmsCodeTokenGranter 支持授权模式 sms_code 根据接口传值 grant_type = sms_code 的值匹配到此授权者
	 * 匹配逻辑详见下面的两个方法
	 *
	 * @see CompositeTokenGranter#grant(String, TokenRequest)
	 * @see AbstractTokenGranter#grant(String, TokenRequest)
	 */
	private static final String GRANT_TYPE = "sms_code";

	private final AuthenticationManager authenticationManager;

	public FxzSmsCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
			AuthenticationManager authenticationManager) {
		super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

		Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());

		// 手机号
		String mobile = parameters.get("mobile");
		// 短信验证码
		String code = parameters.get("code");

		parameters.remove("code");

		Authentication userAuth = new FxzSmsCodeAuthenticationToken(mobile, code);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);

		try {
			userAuth = this.authenticationManager.authenticate(userAuth);
		}
		catch (AccountStatusException | BadCredentialsException e) {
			throw new InvalidGrantException(e.getMessage());
		}

		if (userAuth != null && userAuth.isAuthenticated()) {
			OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
			return new OAuth2Authentication(storedOAuth2Request, userAuth);
		}
		else {
			throw new InvalidGrantException("Could not authenticate user: " + mobile);
		}
	}

}
