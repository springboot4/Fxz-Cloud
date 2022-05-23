package com.fxz.common.security.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.security.entity.FxzAuthUser;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Spring security 工具类
 *
 * @author fxz
 */
@UtilityClass
public class SecurityUtil {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public FxzAuthUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof FxzAuthUser) {
			return (FxzAuthUser) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	@SneakyThrows
	public FxzAuthUser getUser() {
		Authentication authentication = getAuthentication();
		FxzAuthUser userDetail = getUser(authentication);
		if (userDetail == null) {
			throw new FxzException("获取用户信息失败");
		}
		return userDetail;
	}

	/**
	 * 获取当前令牌内容
	 * @return String 令牌内容
	 */
	public String getCurrentTokenValue() {
		if (ObjectUtil.isNotNull(getAuthentication())) {
			Object details = getAuthentication().getDetails();
			if (details instanceof OAuth2AuthenticationDetails) {
				return ((OAuth2AuthenticationDetails) details).getTokenValue();
			}
		}
		return StringPool.EMPTY;
	}

	/**
	 * 获取登录认证的客户端ID
	 * <p>
	 * 兼容两种方式获取OAuth2客户端信息（client_id、client_secret） 方式一：client_id、client_secret放在请求路径中
	 * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA==
	 * 明文等于 client:secret
	 */
	@SneakyThrows
	public String getOAuth2ClientId() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 从请求路径中获取
		String clientId = request.getParameter(SecurityConstants.CLIENT_ID);
		if (StrUtil.isNotBlank(clientId)) {
			return clientId;
		}

		// 从请求头获取
		String basic = request.getHeader(SecurityConstants.AUTHORIZATION_KEY);
		if (StrUtil.isNotBlank(basic) && (basic.startsWith(SecurityConstants.BASIC_PREFIX)
				|| basic.startsWith(SecurityConstants.BASIC_PREFIX_LOW))) {
			basic = basic.replace(SecurityConstants.BASIC_PREFIX, Strings.EMPTY);
			basic = basic.replace(SecurityConstants.BASIC_PREFIX_LOW, Strings.EMPTY);

			String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)),
					StandardCharsets.UTF_8);
			// client:secret
			clientId = basicPlainText.split(":")[0];
		}
		return clientId;
	}

	public String getAuthType() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 从请求路径中获取
		return request.getParameter(SecurityConstants.AUTH_TYPE);
	}

}