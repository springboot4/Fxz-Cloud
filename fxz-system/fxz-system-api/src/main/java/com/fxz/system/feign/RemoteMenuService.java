package com.fxz.system.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-06 14:50
 */
@FeignClient(contextId = "remoteMenuService", value = FxzServerConstant.FXZ_SERVER_SYSTEM)
public interface RemoteMenuService {

	/**
	 * 通过用户名查询权限信息
	 * @param username 用户名称
	 * @return 权限信息
	 */
	@GetMapping("/menu/findUserPermissions/{username}")
	public Set<String> findUserPermissions(@PathVariable("username") String username);

}
