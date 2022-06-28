package com.fxz.common.websocket.service;

import com.fxz.common.websocket.handler.UserNoticeWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/28 10:45
 */
@Service
@RequiredArgsConstructor
public class UserWsNoticeService {

	private final UserNoticeWebSocketHandler noticeWebSocketHandler;

	/**
	 * 服务端发送消息给客户端(指定用户)
	 */
	public <T> void sendMessageByUser(String message, Long userId) {
		noticeWebSocketHandler.sendMessageByUserId(message, userId);
	}

	/**
	 * 服务端发送消息给客户端(指定用户组)
	 */
	public <T> void sendMessageByUsers(String message, List<Long> userIds) {
		noticeWebSocketHandler.sendMessageByUsers(message, userIds);
	}

	/**
	 * 服务端发送消息给客户端(全体发送)
	 */
	public <T> void sendMessageByAll(String message) {
		noticeWebSocketHandler.sendMessageByAll(message);
	}

}
