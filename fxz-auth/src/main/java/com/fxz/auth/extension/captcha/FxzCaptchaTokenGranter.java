package com.fxz.auth.extension.captcha;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.fxz.common.core.constant.FxzConstant;
import com.fxz.common.core.exception.FxzException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 声明授权者 CaptchaTokenGranter 支持授权模式 captcha 根据接口传值 grant_type = captcha 的值匹配到此授权者
 * 匹配逻辑详见下面的两个方法
 *
 * @author fxz
 * @see org.springframework.security.oauth2.provider.CompositeTokenGranter#grant(String,
 * TokenRequest)
 * @see org.springframework.security.oauth2.provider.token.AbstractTokenGranter#grant(String,
 * TokenRequest)
 */
@Slf4j
public class FxzCaptchaTokenGranter extends AbstractTokenGranter {

	private static final String GRANT_TYPE = "captcha";

	private final AuthenticationManager authenticationManager;

	private final RedisTemplate<String, Object> redisTemplate;

	public FxzCaptchaTokenGranter(AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
			AuthenticationManager authenticationManager, RedisTemplate redisService) {
		super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
		this.authenticationManager = authenticationManager;
		this.redisTemplate = redisService;
	}

	@SuppressWarnings("all")
	@SneakyThrows
	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
		// 验证码校验逻辑
		String validateCode = parameters.get("validateCode");
		String uuid = parameters.get("uuid");
		log.info("validateCode:{},uuid:{}", validateCode, uuid);

		Assert.isTrue(StrUtil.isNotBlank(validateCode), "验证码不能为空");
		String validateCodeKey = FxzConstant.CODE_PREFIX + uuid;
		log.info("validateCodeKey:{}", validateCodeKey);

		// 从缓存取出正确的验证码和用户输入的验证码比对
		redisTemplate.setValueSerializer(RedisSerializer.json());
		Object o = redisTemplate.opsForValue().get(validateCodeKey);
		String correctValidateCode = Convert.toStr(o);
		log.info("correctValidateCode", correctValidateCode);

		if (!validateCode.equals(correctValidateCode)) {
			throw new FxzException("验证码不正确");
		}
		else {
			redisTemplate.delete(validateCodeKey);
		}

		String username = parameters.get("username");
		String password = parameters.get("password");

		// 移除后续无用参数
		parameters.remove("password");
		parameters.remove("validateCode");
		parameters.remove("uuid");

		// 和密码模式一样的逻辑
		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);

		try {
			userAuth = this.authenticationManager.authenticate(userAuth);
		}
		catch (AccountStatusException | BadCredentialsException var8) {
			throw new InvalidGrantException(var8.getMessage());
		}

		if (userAuth != null && userAuth.isAuthenticated()) {
			OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
			return new OAuth2Authentication(storedOAuth2Request, userAuth);
		}
		else {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}
	}

}