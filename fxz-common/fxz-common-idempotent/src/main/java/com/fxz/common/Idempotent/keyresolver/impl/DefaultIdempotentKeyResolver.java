package com.fxz.common.Idempotent.keyresolver.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.fxz.common.Idempotent.annotation.Idempotent;
import com.fxz.common.Idempotent.keyresolver.IdempotentKeyResolver;
import org.aspectj.lang.JoinPoint;

/**
 * 默认的key解析器,使用方法名和参数进行md5加密生成key
 *
 * @author fxz
 */
public class DefaultIdempotentKeyResolver implements IdempotentKeyResolver {

	@Override
	public String resolver(JoinPoint joinPoint, Idempotent idempotent) {
		String methodName = joinPoint.getSignature().toString();
		String argsStr = StrUtil.join(",", joinPoint.getArgs());
		return SecureUtil.md5(methodName + argsStr);
	}

}
