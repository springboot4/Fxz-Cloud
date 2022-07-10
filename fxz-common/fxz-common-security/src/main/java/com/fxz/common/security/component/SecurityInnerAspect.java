package com.fxz.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.security.annotation.Ojbk;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fxz
 * <p>
 * 内部服务调用是否鉴权切面
 */
@SuppressWarnings("all")
@Slf4j
@Aspect
@RequiredArgsConstructor
public class SecurityInnerAspect implements Ordered {

	private final HttpServletRequest request;

	/**
	 * 环绕处理Ojbk注解，判断是否为服务内部调用
	 * @param point
	 * @param ojbk
	 * @return
	 */
	@SneakyThrows
	@Around("@within(ojbk) || @annotation(ojbk)")
	public Object around(ProceedingJoinPoint point, Ojbk ojbk) {

		// 实际注入的Ojbk实体由表达式后一个注解决定，即取方法上的注解，若方法上无注解，则获取类上的
		if (ojbk == null) {
			Class<?> clazz = point.getTarget().getClass();
			ojbk = AnnotationUtils.findAnnotation(clazz, Ojbk.class);
		}
		String header = request.getHeader(SecurityConstants.FROM);
		if (ojbk.inner() && !StrUtil.equals(SecurityConstants.FROM_IN, header)) {
			log.warn("访问接口 {} 没有权限", point.getSignature().getName());
			throw new AccessDeniedException("Access is denied");
		}
		return point.proceed();
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 1;
	}

}
