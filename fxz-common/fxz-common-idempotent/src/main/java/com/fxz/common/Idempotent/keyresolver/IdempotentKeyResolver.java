package com.fxz.common.Idempotent.keyresolver;

import com.fxz.common.Idempotent.annotation.Idempotent;
import org.aspectj.lang.JoinPoint;

/**
 * @author fxz
 */
public interface IdempotentKeyResolver {

	/**
	 * 解析一个 Key
	 * @param idempotent 幂等注解
	 * @param joinPoint AOP 切面
	 * @return Key
	 */
	String resolver(JoinPoint joinPoint, Idempotent idempotent);

}
