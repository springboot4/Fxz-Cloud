package com.fxz.common.security.config;

import com.fxz.common.core.constant.FxzConstant;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.FxzUserInfoTokenServices;
import com.fxz.common.security.handler.FxzAccessDeniedHandler;
import com.fxz.common.security.handler.FxzAuthExceptionEntryPoint;
import com.fxz.common.security.permission.PermissionService;
import com.fxz.common.security.properties.FxzCloudSecurityProperties;
import com.fxz.common.security.util.SecurityUtil;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-06 18:15
 */
@Slf4j
@AutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(FxzCloudSecurityProperties.class)
public class FxzCloudSecurityAutoConfigure {

	/**
	 * 解决SecurityContextHolder子线程不能获取用户信息
	 */
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
		methodInvokingFactoryBean.setTargetMethod("setStrategyName");
		methodInvokingFactoryBean.setArguments(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		return methodInvokingFactoryBean;
	}

	/**
	 * 接口权限判断工具
	 */
	@Bean("pms")
	public PermissionService permissionService() {
		return new PermissionService();
	}

	/**
	 * 用于处理403类型异常
	 */
	@Primary
	@Bean
	public FxzAccessDeniedHandler accessDeniedHandler() {
		return new FxzAccessDeniedHandler();
	}

	/**
	 * 用于处理401类型异常
	 */
	@Primary
	@Bean
	public FxzAuthExceptionEntryPoint authenticationEntryPoint() {
		return new FxzAuthExceptionEntryPoint();
	}

	/**
	 * 注入密码编码器
	 */
	@Bean
	@ConditionalOnMissingBean(value = PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 配置拦截器,所有请求必须通过网关
	 */
	@Bean
	public FxzServerProtectConfigure fxzServerProtectInterceptor() {
		return new FxzServerProtectConfigure();
	}

	@Bean
	@Primary
	@ConditionalOnMissingBean(DefaultTokenServices.class)
	public FxzUserInfoTokenServices fxzUserInfoTokenServices(ResourceServerProperties properties) {
		return new FxzUserInfoTokenServices(properties.getUserInfoUri(), properties.getClientId());
	}

	/**
	 * 为feign请求头添加令牌
	 */
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return requestTemplate -> {
			String gatewayToken = new String(Base64Utils.encode(FxzConstant.GATEWAY_TOKEN_VALUE.getBytes()));
			requestTemplate.header(FxzConstant.GATEWAY_TOKEN_HEADER, gatewayToken);

			String authorizationToken = SecurityUtil.getCurrentTokenValue();
			if (StringUtils.isNotBlank(authorizationToken)) {
				requestTemplate.header(HttpHeaders.AUTHORIZATION, FxzConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
			}

			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes != null) {
				ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
				HttpServletRequest request = attributes.getRequest();
				// 获取请求头
				Enumeration<String> headerNames = request.getHeaderNames();
				if (headerNames != null) {
					while (headerNames.hasMoreElements()) {
						String name = headerNames.nextElement();
						String values = request.getHeader(name);
						if (name.equals(HttpHeaders.AUTHORIZATION.toLowerCase())
								&& values.contains(SecurityConstants.BASIC_PREFIX.trim())
								|| name.equals(HttpHeaders.CONTENT_LENGTH.toLowerCase())) {
							continue;
						}
						// 将请求头保存到模板中
						requestTemplate.header(name, values);
					}
				}
			}
		};
	}

	/**
	 * 让DispatcherServlet向子线程传递RequestContext
	 * @param servlet servlet
	 * @return 注册bean
	 */
	@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet servlet) {
		servlet.setThreadContextInheritable(true);
		return new ServletRegistrationBean<>(servlet, "/**");
	}

}
