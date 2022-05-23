package com.fxz.common.Idempotent.keyresolver.impl;

import cn.hutool.core.util.ArrayUtil;
import com.fxz.common.Idempotent.annotation.Idempotent;
import com.fxz.common.Idempotent.keyresolver.IdempotentKeyResolver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * spring el表达式key解析器
 *
 * @author fxz
 */
public class ExpressionIdempotentKeyResolver implements IdempotentKeyResolver {

	/**
	 * 获取方法参数名
	 */
	private final ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

	/**
	 * 参数解析器
	 */
	private final ExpressionParser expressionParser = new SpelExpressionParser();

	@Override
	public String resolver(JoinPoint joinPoint, Idempotent idempotent) {
		// 获得被拦截方法参数名列表
		Method method = getMethod(joinPoint);
		// 获取方法参数
		Object[] args = joinPoint.getArgs();
		// 获取到参数名
		String[] parameterNames = this.parameterNameDiscoverer.getParameterNames(method);

		// 准备 Spring EL 表达式解析的上下文
		StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
		if (ArrayUtil.isNotEmpty(parameterNames)) {
			for (int i = 0; i < parameterNames.length; i++) {
				evaluationContext.setVariable(parameterNames[i], args[i]);
			}
		}

		// 解析参数
		Expression expression = expressionParser.parseExpression(idempotent.keyArg());

		return expression.getValue(evaluationContext, String.class);
	}

	private static Method getMethod(JoinPoint point) {
		// 处理，声明在类上的情况
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		if (!method.getDeclaringClass().isInterface()) {
			return method;
		}

		// 处理，声明在接口上的情况
		try {
			return point.getTarget().getClass().getDeclaredMethod(point.getSignature().getName(),
					method.getParameterTypes());
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
