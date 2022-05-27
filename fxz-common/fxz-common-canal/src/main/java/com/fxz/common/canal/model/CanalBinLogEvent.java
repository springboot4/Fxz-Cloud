package com.fxz.common.canal.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author fxz
 */
@Data
public class CanalBinLogEvent {

	/**
	 * 事件ID,没有实际意义
	 */
	private Long id;

	/**
	 * 当前更变前节点数据
	 */
	private List<Map<String, String>> old;

	/**
	 * 当前更变后节点数据
	 */
	private List<Map<String, String>> data;

	/**
	 * 主键行名称列表
	 */
	private List<String> pkNames;

	/**
	 * 类型 UPDATE\INSERT\DELETE\QUERY
	 */
	private String type;

	/**
	 * binlog execute time
	 */
	private Long es;

	/**
	 * dml build timestamp
	 */
	private Long ts;

	/**
	 * 执行的sql,不一定存在
	 */
	private String sql;

	/**
	 * 数据库名称
	 */
	private String database;

	/**
	 * 表名称
	 */
	private String table;

	/**
	 * SQL类型映射
	 */
	private Map<String, Integer> sqlType;

	/**
	 * MySQL字段类型映射
	 */
	private Map<String, String> mysqlType;

	/**
	 * 是否DDL
	 */
	private Boolean isDdl;

}
