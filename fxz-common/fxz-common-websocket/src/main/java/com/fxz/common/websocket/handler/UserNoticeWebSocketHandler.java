package com.fxz.common.websocket.handler;

import com.fxz.common.websocket.manager.WebSocketSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket用户全局通知
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/6/28 09:49
 */
@Slf4j
public class UserNoticeWebSocketHandler extends TextWebSocketHandler {

	/**
	 * 连接管理器
	 */
	private static final WebSocketSessionManager wsManager = new WebSocketSessionManager();

	/**
	 * 记录当前在线连接数
	 */
	private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

	/**
	 * 连接建立成功调用的方法，对应原生注解里的@OnOpen
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		Long userId = (Long) session.getAttributes().get("userId");
		wsManager.addSession(String.valueOf(userId), session);
		ONLINE_COUNT.incrementAndGet(); // 在线数加1
		log.info("有新连接加入：{}，当前在线人数为：{}", userId, ONLINE_COUNT.get());
	}

	/**
	 * 连接关闭时调用的方法，对应原生注解里的@OnClose
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		// 在线数减1
		ONLINE_COUNT.decrementAndGet();
		String userId = wsManager.getIdBySession(session);
		wsManager.removeSession(session);
		log.info("连接关闭：{}，当前在线人数为：{}", userId, ONLINE_COUNT.get());
	}

	/**
	 * 发生错误时调用的方法，对应原生注解里的@OnError
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) {
		log.error("{} 发生错误", session.getId());
		throwable.printStackTrace();
	}

	/**
	 * 服务器发送消息给客户端(指定用户)
	 */
	public void sendMessageByUserId(String message, Long userId) {
		try {
			List<WebSocketSession> sessions = wsManager.getSessionsById(String.valueOf(userId));

			for (WebSocketSession session : sessions) {
				session.sendMessage(new TextMessage(message));
			}
		}
		catch (Exception e) {
			log.error("服务端发送消息给客户端失败：", e);
		}
	}

	/**
	 * 服务器发送消息给客户端(指定用户组)
	 */
	public void sendMessageByUsers(String message, List<Long> userIds) {
		for (Long userId : userIds) {
			this.sendMessageByUserId(message, userId);
		}
	}

	/**
	 * 服务器发送消息给客户端(全体发送)
	 */
	public void sendMessageByAll(String message) {
		try {
			List<WebSocketSession> sessions = wsManager.getSessions();
			for (WebSocketSession session : sessions) {
				session.sendMessage(new TextMessage(message));
			}
		}
		catch (Exception e) {
			log.error("服务端发送消息给客户端失败：", e);
		}
	}

}
