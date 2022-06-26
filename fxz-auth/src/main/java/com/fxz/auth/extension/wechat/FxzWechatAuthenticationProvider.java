package com.fxz.auth.extension.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import com.fxz.auth.service.member.FxzMemberUserDetailsServiceImpl;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.common.mp.result.ResultCode;
import com.fxz.mall.member.entity.Member;
import com.fxz.mall.member.feign.RemoteMemberService;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

/**
 * 微信认证提供者
 *
 * @author fxz
 */
@Data
public class FxzWechatAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	private WxMaService wxMaService;

	private RemoteMemberService memberFeignClient;

	/**
	 * 微信认证
	 */
	@SneakyThrows
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		FxzWechatAuthenticationToken authenticationToken = (FxzWechatAuthenticationToken) authentication;
		String code = (String) authenticationToken.getPrincipal();

		WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
		String openid = sessionInfo.getOpenid();

		Result<Member> memberAuthResult = memberFeignClient.loadUserByOpenId(openid, SecurityConstants.FROM_IN);

		// 微信用户不存在，注册成为新会员
		if (memberAuthResult != null && ResultCode.USER_NOT_EXIST.getCode().equals(memberAuthResult.getCode())) {

			String sessionKey = sessionInfo.getSessionKey();
			String encryptedData = authenticationToken.getEncryptedData();
			String iv = authenticationToken.getIv();

			// 解密 encryptedData 获取用户信息
			WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

			Member member = new Member();
			BeanUtil.copyProperties(userInfo, member);
			member.setOpenid(openid);

			memberFeignClient.addMember(member, SecurityConstants.FROM_IN);
		}

		UserDetails userDetails = ((FxzMemberUserDetailsServiceImpl) userDetailsService).loadUserByOpenId(openid);

		FxzWechatAuthenticationToken result = new FxzWechatAuthenticationToken(userDetails, new HashSet<>());
		result.setDetails(authentication.getDetails());

		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return FxzWechatAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
