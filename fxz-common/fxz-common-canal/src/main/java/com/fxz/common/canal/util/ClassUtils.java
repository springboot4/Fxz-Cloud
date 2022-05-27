package com.fxz.common.canal.util;

import java.lang.reflect.Constructor;

/**
 * @author fxz
 */
public enum ClassUtils {

	/**
	 * 单例
	 */
	X;

	public <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes) {
		AssertUtils.X.notNull(clazz, "Class must not be null");
		try {
			return clazz.getConstructor(paramTypes);
		}
		catch (NoSuchMethodException var3) {
			return null;
		}
	}

}
