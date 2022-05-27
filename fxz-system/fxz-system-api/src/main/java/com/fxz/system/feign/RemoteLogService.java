package com.fxz.system.feign;

import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.system.dto.OperLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-07 14:50
 */
@FeignClient(contextId = "remoteLogService", value = FxzServerConstant.FXZ_SERVER_SYSTEM)
public interface RemoteLogService {

	/**
	 * 日志保存
	 */
	@PostMapping(value = "/operLog/add")
	public void add(@RequestBody OperLogDto operLogDto);

}
