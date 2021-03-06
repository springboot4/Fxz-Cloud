package com.fxz.system.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.fxz.common.Idempotent.annotation.Idempotent;
import com.fxz.common.Idempotent.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.fxz.common.core.exception.ErrorCodes;
import com.fxz.common.core.utils.MsgUtils;
import com.fxz.common.mp.result.Result;
import com.fxz.common.mq.redis.core.RedisMQTemplate;
import com.fxz.common.redis.cache.support.CacheMessage;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.common.sequence.service.Sequence;
import com.fxz.common.websocket.service.UserWsNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

	private final Sequence fxzSequence;

	private final Sequence cloudSequence;

	private final UserWsNoticeService userWsNoticeService;

	private final RedisMQTemplate redisMQTemplate;

	@Ojbk
	@CacheEvict(value = "demo", key = "#id")
	@GetMapping("/cache/evict")
	public Result<String> CacheEvict(Long id) {
		redisMQTemplate.send(new CacheMessage());
		return Result.success(id.toString());
	}

	@Ojbk
	@Cacheable(value = "demo", key = "#id")
	@GetMapping("/cache/demo")
	public Result<String> get(Long id) {
		return Result.success(id.toString());
	}

	@Ojbk
	@GetMapping("/websocket")
	public Result<Void> websocket() {
		userWsNoticeService.sendMessageByAll("???????????????");
		return Result.success();
	}

	@SneakyThrows
	@Ojbk
	@GetMapping("/seqTestZdy")
	public Result<String> seqTestZdy() {
		return Result.success(fxzSequence.nextValue("fxz") + ":" + cloudSequence.nextValue("cloud"));
	}

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
		return Result.failed(MsgUtils.getMessage(ErrorCodes.SYS_TEST_MESSAGE_STR, "??????1", "??????2"));
	}

	@Ojbk
	@GetMapping("/ipTest")
	public Result<Object> getDeptTree(HttpServletRequest request) {
		String ip = ServletUtil.getClientIP(request);
		log.info("ip:{}", ip);
		return Result.success(ip);
	}

	@Idempotent(timeout = 10, message = "??????????????????????????????", keyResolver = ExpressionIdempotentKeyResolver.class,
			keyArg = "#str")
	@Ojbk
	@GetMapping("/idempotent")
	public Result<Void> testIdempotent(String str) {
		log.info("????????????");
		return Result.success();
	}

	@GetMapping("/authTest")
	public Result<Void> authTest() {
		log.info("authTest.....");
		return Result.success();
	}

}
