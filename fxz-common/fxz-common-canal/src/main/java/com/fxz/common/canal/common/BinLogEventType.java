package com.fxz.common.canal.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author fxz
 * @description binlog事件类型
 */
@RequiredArgsConstructor
@Getter
public enum BinLogEventType {

	/**
	 * 查询
	 */
	QUERY("QUERY", "查询"),

	/**
	 * 新增
	 */
	INSERT("INSERT", "新增"),

	/**
	 * 更新
	 */
	UPDATE("UPDATE", "更新"),

	/**
	 * 删除
	 */
	DELETE("DELETE", "删除"),

	/**
	 * 列修改操作
	 */
	ALTER("ALTER", "列修改操作"),

	/**
	 * 表创建
	 */
	CREATE("CREATE", "表创建"),

	/**
	 * 未知
	 */
	UNKNOWN("UNKNOWN", "未知");

	private final String type;

	private final String description;

	public static BinLogEventType fromType(String type) {
		for (BinLogEventType binLogType : BinLogEventType.values()) {
			if (binLogType.getType().equals(type)) {
				return binLogType;
			}
		}
		return BinLogEventType.UNKNOWN;
	}

}
