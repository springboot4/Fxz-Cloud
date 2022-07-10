package com.fxz.common.Idempotent.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.fxz.common.Idempotent.annotation.Idempotent;
import com.fxz.common.Idempotent.keyresolver.IdempotentKeyResolver;
import com.fxz.common.Idempotent.redis.IdempotentRedisDAO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.Assert;

/**
 * 幂等性切面
 *
 * @author Fxz
 * @version 1.0
 * @date 2022-04-11 21:22
 */
@Aspect
@Slf4j
public class IdempotentAspect {

	private final IdempotentRedisDAO idempotentRedisDAO;

	public IdempotentAspect(IdempotentRedisDAO idempotentRedisDAO) {
		this.idempotentRedisDAO = idempotentRedisDAO;
	}

	/**
	 * 对带有Idempotent注解的进行幂等处理，使用前置通知
	 */
	@Before("@annotation(idempotent)")
	public void beforePointCut(JoinPoint joinPoint, Idempotent idempotent) {
		// 获得 IdempotentKeyResolver
		IdempotentKeyResolver keyResolver = SpringUtil.getBean(idempotent.keyResolver());
		Assert.notNull(keyResolver, "找不到对应的 IdempotentKeyResolver");

		// 解析 Key
		String key = keyResolver.resolver(joinPoint, idempotent);

		// 锁定 Key
		boolean success = idempotentRedisDAO.setIfAbsent(key, idempotent.timeout(), idempotent.timeUnit());

		// 锁定失败，抛出异常
		if (!success) {
			log.info("[beforePointCut][方法({}) 参数({}) 存在重复请求]", joinPoint.getSignature().toString(),
					joinPoint.getArgs());
			throw new RuntimeException(idempotent.message());
		}

	}

}
