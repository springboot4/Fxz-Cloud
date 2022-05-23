package com.fxz.system.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.constant.FxzServerConstant;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-14 21:30
 */
@FeignClient(contextId = "remoteTokenService", value = FxzServerConstant.FXZ_AUTH)
public interface RemoteTokenService {

	/**
	 * 删除token
	 */
	@DeleteMapping("/token/{token}")
	public Result<Void> removeToken(@PathVariable("token") String token);

	/**
	 * 分页查询token
	 */
	@GetMapping("/token/page")
	public Result<PageResult> tokenList(@RequestParam("page") Page page);

}
