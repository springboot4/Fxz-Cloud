package com.fxz.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.fxz.common.log.annotation.OperLogAnn;
import com.fxz.common.log.enums.BusinessStatus;
import com.fxz.common.log.service.AsyncLogService;
import com.fxz.common.mp.result.Result;
import com.fxz.system.entity.OperLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 操作日志记录处理切面
 *
 * @author ruoyi
 */
@Slf4j
@Aspect
public class LogAspect {

	@Autowired
	private AsyncLogService asyncLogService;

	/**
	 * 处理OperLog注解，使用环绕通知
	 */
	@Around("@annotation(operLogAnn)")
	public Result<Object> around(ProceedingJoinPoint point, OperLogAnn operLogAnn) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		OperLog operLog = new OperLog();

		// 模块名
		String title = operLogAnn.title();

		// 获取ip地址
		String ip = ServletUtil.getClientIP(request);

		// 请求路径
		String path = URLUtil.getPath(request.getRequestURI());

		// 操作用户
		AtomicReference<String> userName = new AtomicReference<>();
		Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).ifPresent(authentication -> {
			userName.set(authentication.getName());
		});

		// 方法名称
		String method = point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()";

		// 请求方式
		String requestMethod = request.getMethod();

		// 请求参数
		String param = null;
		if (operLogAnn.isSaveRequestData()) {
			param = HttpUtil.toParams(request.getParameterMap());
		}

		// 业务类型
		Integer businessType = operLogAnn.businessType().getValue();

		Long startTime = System.currentTimeMillis();

		log.info("方法:{}", method);
		Result<Object> result = null;
		try {
			result = (Result<Object>) point.proceed();
		}
		catch (Throwable e) {
			operLog.setStatus(BusinessStatus.FAIL.getValue());
			operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
		}
		finally {
			Long endTime = System.currentTimeMillis();
			// 方法执行时间
			long time = endTime - startTime;

			// 赋值
			operLog.setTitle(title);
			operLog.setOperIp(ip);
			operLog.setOperUrl(path);
			operLog.setOperName(userName.get());
			operLog.setMethod(method);
			operLog.setRequestMethod(requestMethod);
			operLog.setOperParam(param);
			operLog.setBusinessType(businessType);
			operLog.setTime(time);

			log.info("异步保存:{}", Thread.currentThread().getId());
			// 异步保存
			asyncLogService.saveSysLog(operLog);
		}
		return result;
	}

}
