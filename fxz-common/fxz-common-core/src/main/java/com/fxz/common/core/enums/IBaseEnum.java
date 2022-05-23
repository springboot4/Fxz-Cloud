package com.fxz.common.core.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;
import java.util.Objects;

/**
 * 枚举通用接口
 *
 * @author fxz
 */
public interface IBaseEnum<T> {

	/**
	 * 获取枚举值
	 * @return 枚举值
	 */
	T getValue();

	/**
	 * 获取枚举标签
	 * @return 枚举标签
	 */
	String getLabel();

	/**
	 * 根据枚举值和枚举类获取枚举
	 * @param value 枚举值
	 * @param clazz 枚举类型
	 * @return 枚举
	 */
	static <E extends Enum<E> & IBaseEnum> E getEnumByValue(Object value, Class<E> clazz) {
		Objects.requireNonNull(value);

		// 获取类型下的所有枚举
		EnumSet<E> allEnums = EnumSet.allOf(clazz);
		return allEnums.stream().filter(e -> ObjectUtil.equal(e.getValue(), value)).findFirst().orElse(null);
	}

	/**
	 * 根据枚举值和枚举类获取枚举标签
	 * @param value 枚举值
	 * @param clazz 枚举类型
	 * @return 枚举标签
	 */
	static <E extends Enum<E> & IBaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
		E matchEnum = getEnumByValue(value, clazz);

		String label = null;
		if (Objects.nonNull(matchEnum)) {
			label = matchEnum.getLabel();
		}

		return label;
	}

	/**
	 * 根据枚举标签和枚举类获取枚举
	 * @param label 枚举标签
	 * @param clazz 枚举类型
	 * @return 枚举
	 */
	static <E extends Enum<E> & IBaseEnum> E getEnumByLabel(String label, Class<E> clazz) {
		Objects.requireNonNull(label);

		// 获取类型下的所有枚举
		EnumSet<E> allEnums = EnumSet.allOf(clazz);

		return allEnums.stream().filter(e -> ObjectUtil.equal(e.getLabel(), label)).findFirst().orElse(null);
	}

	/**
	 * 根据枚举标签和枚举类获取枚举值
	 * @param label 枚举标签
	 * @param clazz 枚举类型
	 * @return 枚举值
	 */
	static <E extends Enum<E> & IBaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
		E matchEnum = getEnumByLabel(label, clazz);

		Object value = null;
		if (Objects.nonNull(matchEnum)) {
			value = matchEnum.getValue();
		}

		return value;
	}

}
