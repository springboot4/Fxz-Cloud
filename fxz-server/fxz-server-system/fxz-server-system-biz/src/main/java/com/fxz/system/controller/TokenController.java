package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.log.annotation.OperLogAnn;
import com.fxz.common.log.enums.BusinessType;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.system.feign.RemoteTokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-14 21:32
 */
@RequiredArgsConstructor
@RestController("/token")
public class TokenController {

	private final RemoteTokenService remoteTokenService;

	/**
	 * 删除token
	 */
	@OperLogAnn(title = "强退", businessType = BusinessType.FORCE)
	@SneakyThrows
	@DeleteMapping("/token/{token}")
	public Result<Void> removeToken(@PathVariable("token") String token) {
		return remoteTokenService.removeToken(token);
	}

	/**
	 * 分页查询token
	 * @param page 分页参数
	 * @return
	 */
	@GetMapping("/token/page")
	public Result<PageResult> tokenList(Page page) {
		return remoteTokenService.tokenList(page);
	}

}
