package com.fxz.system.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.core.entity.DeptDataPermissionRespDTO;
import com.fxz.common.mp.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-13 21:39
 */
@FeignClient(contextId = "remoteRoleService", value = FxzServerConstant.FXZ_SERVER_SYSTEM)
public interface RemoteRoleService {

	/**
	 * 获取当前用户角色下的数据权限
	 */
	@GetMapping("/role/getDataPermission")
	public Result<DeptDataPermissionRespDTO> getDataPermission();

}
