package com.fxz.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fxz
 */

@Getter
@AllArgsConstructor
public enum DictTypeEnum {

	/**
	 * 字典类型-业务类型
	 */
	BIZ("0", "业务类"),

	/**
	 * 字典类型-系统内置（不可修改）
	 */
	SYSTEM("1", "系统内置");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
