package com.fxz.gateway.router;

import com.fxz.gateway.handler.EasyCaptchaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 验证码路由
 *
 * @author fxz
 */
@Configuration
public class EasyCaptchaRouter {

	@Bean
	public RouterFunction<ServerResponse> captchaRouterFunction(EasyCaptchaHandler easyCaptchaHandler) {
		return RouterFunctions.route(
				RequestPredicates.GET("/captcha").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				easyCaptchaHandler::handle);
	}

}
