package com.fxz.auth.configure;

import com.fxz.auth.extension.captcha.FxzCaptchaTokenGranter;
import com.fxz.auth.extension.mobile.FxzSmsCodeTokenGranter;
import com.fxz.auth.extension.wechat.FxzWechatTokenGranter;
import com.fxz.auth.properties.FxzAuthProperties;
import com.fxz.auth.service.FxzPreAuthenticatedUserDetailsService;
import com.fxz.auth.service.member.FxzMemberUserDetailsServiceImpl;
import com.fxz.auth.service.user.FxzUserDetailServiceImpl;
import com.fxz.auth.translator.FxzWebResponseExceptionTranslator;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.entity.FxzAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author Fxz
 * @version 1.0
 * @Description 认证服务器配置
 * @date 2021-11-27 16:10
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class FxzAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

	private final DataSource dataSource;

	private final RedisTemplate redisTemplate;

	private final AuthenticationManager authenticationManager;

	private final RedisConnectionFactory redisConnectionFactory;

	private final FxzUserDetailServiceImpl fxzUserDetailService;

	private final FxzMemberUserDetailsServiceImpl fxzMemberUserDetailsService;

	private final FxzAuthProperties authProperties;

	private final FxzWebResponseExceptionTranslator fxzWebResponseExceptionTranslator;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		// 允许表单认证
		security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(fxzClientDetailsService());
	}

	@Override
	@SuppressWarnings("all")
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

		// 获取原有默认授权模式(授权码模式、密码模式、客户端模式、简化模式)的授权者
		List<TokenGranter> granterList = new ArrayList<>(Arrays.asList(endpoints.getTokenGranter()));

		// 添加验证码授权模式授权者
		granterList.add(new FxzCaptchaTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory(), authenticationManager, redisTemplate));

		// 添加手机短信验证码授权模式的授权者
		granterList.add(new FxzSmsCodeTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory(), authenticationManager));

		// 添加微信授权模式的授权者
		granterList.add(new FxzWechatTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory(), authenticationManager));

		CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);

		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancer()).authenticationManager(authenticationManager)
				.tokenServices(defaultTokenServices()).pathMapping("/oauth/confirm_access", "/token/confirm_access")
				.tokenGranter(compositeTokenGranter).exceptionTranslator(fxzWebResponseExceptionTranslator);
	}

	/**
	 * 客户端信息加载处理
	 * @return ClientDetailsService
	 */
	@Bean
	public ClientDetailsService fxzClientDetailsService() {
		JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		return clientDetailsService;
	}

	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		redisTokenStore.setPrefix(SecurityConstants.TOKEN_PREFIX);
		return redisTokenStore;
	}

	@Bean
	public UserAuthenticationConverter userAuthenticationConverter() {
		DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
		defaultUserAuthenticationConverter.setUserDetailsService(fxzUserDetailService);
		return defaultUserAuthenticationConverter;
	}

	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		// 为DefaultTokenServices设置七大参数
		DefaultTokenServices tokenServices = new DefaultTokenServices();

		// token采用redis存储
		tokenServices.setTokenStore(tokenStore());
		// refresh_token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
		// 1 重复使用：access_token过期刷新时， refresh_token过期时间未改变，仍以初次生成的 时间为准
		// 2 非重复使用：access_token过期刷新时，
		// refresh_token过期时间延续，在refresh_token有效期内刷新便永不失效达到无需再次登录的目的
		tokenServices.setSupportRefreshToken(true);
		// token增强，添加了字段
		tokenServices.setTokenEnhancer(tokenEnhancer());
		// token令牌有效时间
		tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
		// 刷新token有效时间
		tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
		// 设置ClientDetailsService
		tokenServices.setClientDetailsService(fxzClientDetailsService());

		// 多用户体系下，认证客户端ID和 UserDetailService 的映射Map
		Map<String, UserDetailsService> clientUserDetailsServiceMap = new HashMap<>();
		// 系统管理客户端使用fxzUserDetailService加载用户信息
		clientUserDetailsServiceMap.put(SecurityConstants.ADMIN_CLIENT_ID, fxzUserDetailService);
		// Android、IOS、H5 移动客户端使用fxzMemberUserDetailsService加载用户信息
		clientUserDetailsServiceMap.put(SecurityConstants.APP_CLIENT_ID, fxzMemberUserDetailsService);
		// 微信小程序客户端使用fxzMemberUserDetailsService加载用户信息
		clientUserDetailsServiceMap.put(SecurityConstants.WEAPP_CLIENT_ID, fxzMemberUserDetailsService);

		// 重写预认证提供者替换其AuthenticationManager，可自定义根据客户端ID和认证方式区分用户体系获取认证用户信息
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(
				new FxzPreAuthenticatedUserDetailsService<>(clientUserDetailsServiceMap));
		tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));

		return tokenServices;
	}

	/**
	 * token 生成接口输出增强
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			final Map<String, Object> additionalInfo = new HashMap<>(8);
			String clientId = authentication.getOAuth2Request().getClientId();
			additionalInfo.put(SecurityConstants.CLIENT_ID, clientId);
			additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);

			FxzAuthUser user = (FxzAuthUser) authentication.getUserAuthentication().getPrincipal();
			additionalInfo.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
			additionalInfo.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		};
	}

}
