package com.fxz.common.websocket;

import com.fxz.common.websocket.handler.UserNoticeWebSocketHandler;
import com.fxz.common.websocket.interceptor.UserNoticeWebSocketInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/28 09:23
 */
@AutoConfiguration
@ComponentScan
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

	private final UserNoticeWebSocketHandler userNoticeWebSocketHandler;

	private final UserNoticeWebSocketInterceptor userNoticeWebSocketInterceptor;

	/**
	 * 注入ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
	 */
	@Bean
	@ConditionalOnMissingBean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
				// WebSocket 连接处理器
				.addHandler(userNoticeWebSocketHandler, "/websocket/user")
				// WebSocket 拦截器
				.addInterceptors(userNoticeWebSocketInterceptor)
				// 允许跨域
				.setAllowedOrigins("*");
	}

}
