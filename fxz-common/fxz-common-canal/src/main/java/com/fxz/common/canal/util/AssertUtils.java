package com.fxz.common.canal.util;

import java.util.Objects;

/**
 * @author fxz
 */
public enum AssertUtils {

	/**
	 * 单例
	 */
	X;

	public void notNull(Object object, String message) {
		if (Objects.isNull(object)) {
			throw new IllegalArgumentException(message);
		}
	}

	public void isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message);
		}
	}

	public void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

}
