package com.fxz.gateway.configure;

import com.fxz.gateway.handler.FxzGatewayExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class FxzGatewayErrorConfigure {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ErrorWebExceptionHandler errorWebExceptionHandler() {
		return new FxzGatewayExceptionHandler();
	}

}
