package com.fxz.common.websocket.interceptor;

import com.fxz.common.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Objects;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/28 10:27
 */
@Slf4j
@Component
public class UserNoticeWebSocketInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		log.info("websocket鉴权");
		// 由于 WebSocket 握手是由 http 升级的，携带 token 已经被 Security 拦截验证了，所以可以直接获取到用户
		Long userId = SecurityUtil.getUser().getUserId();
		log.info("userid:{}", userId);

		if (Objects.isNull(userId)) {
			return Boolean.FALSE;
		}
		attributes.put("userId", userId);
		return Boolean.TRUE;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

}
