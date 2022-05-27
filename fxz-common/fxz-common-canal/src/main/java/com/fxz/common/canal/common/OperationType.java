package com.fxz.common.canal.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author fxz
 * @description sql类型
 */
@RequiredArgsConstructor
@Getter
public enum OperationType {

	/**
	 * DML
	 */
	DML("dml", "DML语句"),

	/**
	 * DDL
	 */
	DDL("ddl", "DDL语句");

	private final String type;

	private final String description;

}
