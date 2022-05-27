package com.fxz.system.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.system.entity.SystemUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-06 14:50
 */
@FeignClient(contextId = "remoteUserService", value = FxzServerConstant.FXZ_SERVER_SYSTEM)
public interface RemoteUserService {

	/**
	 * 通过用户名查找用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	@GetMapping("/user/findByName/{username}")
	public SystemUser findByName(@PathVariable("username") String username);

	/**
	 * 通过手机号查找用户信息
	 * @param mobile 手机号
	 * @return 用户信息
	 */
	@GetMapping("/user/findByMobile/{mobile}")
	public SystemUser findByMobile(@PathVariable("mobile") String mobile,
			@RequestHeader(SecurityConstants.FROM) String from);

}
