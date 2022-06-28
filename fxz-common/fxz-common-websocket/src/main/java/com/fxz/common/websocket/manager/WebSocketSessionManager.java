package com.fxz.common.websocket.manager;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * websocket连接管理器
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/6/28 10:02
 */
public class WebSocketSessionManager {

	/**
	 * session缓存,sessionId 与 session 的映射关系
	 */
	private static final Map<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

	/**
	 * sessionId 与 连接id 的映射关系 n:1
	 */
	private static final Map<String, String> SID_ID = new ConcurrentHashMap<>();

	/**
	 * 连接id 与 sessionId 的映射关系 1:n
	 */
	private static final Map<String, List<String>> ID_SID = new ConcurrentHashMap<>();

	/**
	 * 添加会话session关联
	 */
	public void addSession(String id, WebSocketSession session) {
		// 保存sessionId与连接id映射关系
		SID_ID.put(session.getId(), id);

		// 保存sessionId与session映射关系
		SESSION_POOL.put(session.getId(), session);

		// 保存连接id与sessionId的映射关系
		List<String> list = Optional.ofNullable(ID_SID.get(id)).orElse(new CopyOnWriteArrayList<>());
		list.add(session.getId());
		ID_SID.put(id, list);
	}

	/**
	 * 删掉 连接Session
	 */
	public void removeSession(WebSocketSession session) {
		SESSION_POOL.remove(session.getId());
		String id = SID_ID.remove(session.getId());
		Optional.ofNullable(ID_SID.get(id)).ifPresent(list -> list.removeIf(s -> Objects.equals(s, session.getId())));
	}

	/**
	 * 删除
	 */
	public void removeSessionById(String id) {
		List<String> sessionIds = Optional.ofNullable(ID_SID.get(id)).orElse(new ArrayList<>());
		sessionIds.forEach(SESSION_POOL::remove);
		sessionIds.forEach(SID_ID::remove);
		ID_SID.remove(id);

	}

	/**
	 * 根据id获取关联的session列表
	 */
	public List<WebSocketSession> getSessionsById(String id) {
		List<String> sessionIds = Optional.ofNullable(ID_SID.get(id)).orElse(new ArrayList<>());
		return sessionIds.stream().map(SESSION_POOL::get).collect(Collectors.toList());
	}

	/**
	 * 获取所有连接session
	 */
	public ArrayList<WebSocketSession> getSessions() {
		return new ArrayList<>(SESSION_POOL.values());
	}

	/**
	 * 根据session获取连接id
	 */
	public String getIdBySession(WebSocketSession session) {
		return SID_ID.get(session.getId());
	}

	/**
	 * 根据session获取连接id
	 */
	public String getIdBySessionId(String sessionId) {
		return SID_ID.get(sessionId);
	}

}
