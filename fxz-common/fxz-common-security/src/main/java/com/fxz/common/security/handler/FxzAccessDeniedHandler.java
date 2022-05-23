package com.fxz.common.security.handler;

import com.fxz.common.mp.result.Result;
import com.fxz.common.core.utils.FxzUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于处理403类型异常
 *
 * @author Fxz
 * @version 1.0
 * @date 2021-11-27 21:52
 */
public class FxzAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		FxzUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_FORBIDDEN,
				Result.failed("没有权限访问该资源"));
	}

}
