package com.fxz.common.canal.model;

import com.fxz.common.canal.common.BinLogEventType;
import com.fxz.common.canal.common.OperationType;
import lombok.Data;

/**
 * @author fxz
 */
@Data
public class CanalBinLogResult<T> {

	/**
	 * 提取的长整型主键
	 */
	private Long primaryKey;

	/**
	 * binlog事件类型
	 */
	private BinLogEventType binLogEventType;

	/**
	 * 更变前的数据
	 */
	private T beforeData;

	/**
	 * 更变后的数据
	 */
	private T afterData;

	/**
	 * 数据库名称
	 */
	private String databaseName;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * sql语句 - 一般是DDL的时候有用
	 */
	private String sql;

	/**
	 * mysql操作类型
	 */
	private OperationType operationType;

}
