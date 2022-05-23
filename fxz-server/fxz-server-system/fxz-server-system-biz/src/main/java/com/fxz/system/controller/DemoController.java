package com.fxz.system.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.fxz.common.Idempotent.annotation.Idempotent;
import com.fxz.common.Idempotent.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.fxz.common.core.exception.ErrorCodes;
import com.fxz.common.core.utils.MsgUtils;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.common.sequence.service.Sequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 18:33
 */
@Slf4j
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

	// private final Sequence sequence;

	private final Sequence fxzSequence;

	private final Sequence cloudSequence;

	@Ojbk
	@GetMapping("/seqTestZdy")
	public Result<String> seqTestZdy() {
		return Result.success(fxzSequence.nextValue("fxz") + ":" + cloudSequence.nextValue("cloud"));
	}

	/*
	 * @Ojbk
	 *
	 * @GetMapping("/seqTest") public Result<String> seqTest() { String fxz =
	 * sequence.nextValue("fxz"); return Result.success(fxz); }
	 */

	@GetMapping("/security/inheritable")
	public Result<FxzAuthUser> securityInheritable() {
		AtomicReference<FxzAuthUser> user = new AtomicReference<>();

		user.set(SecurityUtil.getUser());
		log.info("user:{},Thread:{}", user, Thread.currentThread().getId());

		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
			user.set(SecurityUtil.getUser());
			log.info("user:{},Thread:{}", user, Thread.currentThread().getId());
		});

		voidCompletableFuture.join();

		return Result.success(user.get());
	}

	@Ojbk
	@GetMapping("/messageTest")
	public Result<String> messageTest() {
		return Result.failed(MsgUtils.getMessage(ErrorCodes.SYS_TEST_MESSAGE_STR, "参数1", "参数2"));
	}

	@Ojbk
	@GetMapping("/ipTest")
	public Result<Object> getDeptTree(HttpServletRequest request) {
		String ip = ServletUtil.getClientIP(request);
		log.info("ip:{}", ip);
		return Result.success(ip);
	}

	@Idempotent(timeout = 10, message = "别发请求，等我执行完", keyResolver = ExpressionIdempotentKeyResolver.class,
			keyArg = "#str")
	@Ojbk
	@GetMapping("/idempotent")
	public Result<Void> testIdempotent(String str) {
		log.info("方法执行");
		return Result.success();
	}

	@GetMapping("/authTest")
	public Result<Void> authTest() {
		log.info("authTest.....");
		return Result.success();
	}

}
