package com.fxz.auth.configure;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.fxz.auth.extension.mobile.FxzSmsCodeAuthenticationProvider;
import com.fxz.auth.extension.wechat.FxzWechatAuthenticationProvider;
import com.fxz.auth.service.member.FxzMemberUserDetailsServiceImpl;
import com.fxz.auth.service.user.FxzUserDetailServiceImpl;
import com.fxz.mall.user.feign.RemoteMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Fxz
 * @version 1.0
 * @Description 开启和Web相关的安全配置
 * @date 2021-11-27 16:00
 */
@Order(1)
@EnableWebSecurity
@RequiredArgsConstructor
public class FxzSecurityConfigure extends WebSecurityConfigurerAdapter {

	private final WxMaService wxMaService;

	private final StringRedisTemplate redisTemplate;

	private final RemoteMemberService remoteMemberService;

	private final FxzUserDetailServiceImpl fxzUserDetailService;

	private final FxzMemberUserDetailsServiceImpl fxzMemberUserDetailsService;

	/**
	 * 必须注入 AuthenticationManager，不然oauth 无法处理四种授权方式
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/token/**", "/oauth/**").and().authorizeRequests().antMatchers("/oauth/**")
				.authenticated().and().formLogin().loginPage("/token/login").loginProcessingUrl("/token/form")
				.permitAll().and().csrf().disable();
	}

	/**
	 * 认证中心静态资源处理
	 * @param web WebSecurity
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/*.html", "/css/**", "/img/**", "/js/**", "/*.ico", "/resource/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(smsCodeAuthenticationProvider())
				.authenticationProvider(wechatAuthenticationProvider());
	}

	/**
	 * 用户名密码认证授权提供者
	 * @return 用户名密码认证授权提供者
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(fxzUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		// 是否隐藏用户不存在异常，默认:true-隐藏；false-抛出异常；
		provider.setHideUserNotFoundExceptions(false);
		return provider;
	}

	/**
	 * 手机验证码认证授权提供者
	 * @return 手机验证码认证授权提供者
	 */
	@Bean
	public FxzSmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
		FxzSmsCodeAuthenticationProvider provider = new FxzSmsCodeAuthenticationProvider();
		provider.setFxzMemberUserDetailsService(fxzMemberUserDetailsService);
		provider.setFxzUserDetailService(fxzUserDetailService);
		provider.setRedisTemplate(redisTemplate);
		return provider;
	}

	/**
	 * 微信认证授权提供者
	 * @return 微信认证授权提供者
	 */
	@Bean
	public FxzWechatAuthenticationProvider wechatAuthenticationProvider() {
		FxzWechatAuthenticationProvider provider = new FxzWechatAuthenticationProvider();
		provider.setUserDetailsService(fxzMemberUserDetailsService);
		provider.setWxMaService(wxMaService);
		provider.setMemberFeignClient(remoteMemberService);
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
