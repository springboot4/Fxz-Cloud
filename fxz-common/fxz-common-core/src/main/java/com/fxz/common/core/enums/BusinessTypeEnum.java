package com.fxz.common.core.enums;

import lombok.Getter;

/**
 * 业务类型枚举
 *
 * @author fxz
 */

public enum BusinessTypeEnum implements IBaseEnum<Integer> {

	/**
	 * 用户业务
	 */
	USER(100, "用户"),
	/**
	 * 会员业务
	 */
	MEMBER(200, "会员"),
	/**
	 * 订单业务
	 */
	ORDER(300, "订单");

	@Getter
	private final Integer value;

	@Getter
	private final String label;

	BusinessTypeEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

}