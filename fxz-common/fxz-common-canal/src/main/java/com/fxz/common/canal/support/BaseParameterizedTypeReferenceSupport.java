package com.fxz.common.canal.support;

import com.fxz.common.canal.util.AssertUtils;
import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author fxz
 */
public abstract class BaseParameterizedTypeReferenceSupport<T> {

	@Getter
	private final Class<T> klass;

	@Getter
	private final Class<?> childKlass;

	@SuppressWarnings("unchecked")
	public BaseParameterizedTypeReferenceSupport() {
		childKlass = getClass();
		Type type = findParameterizedTypeReferenceSubClass(childKlass);
		AssertUtils.X.isInstanceOf(ParameterizedType.class, type, "Type must be a parameterized type");
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		AssertUtils.X.isTrue(actualTypeArguments.length == 1, "Number of type arguments must be 1");
		this.klass = (Class<T>) actualTypeArguments[0];
	}

	/**
	 * 递归搜索ParameterizedType类型引用的父类 - 目的是获取泛型参数类型
	 * @param child child
	 * @return Class
	 */
	private static Type findParameterizedTypeReferenceSubClass(Class<?> child) {
		Type genericSuperclass = child.getGenericSuperclass();
		if (!(genericSuperclass instanceof ParameterizedType)) {
			Class<?> parent = child.getSuperclass();
			if (Object.class == parent) {
				throw new IllegalStateException("Expected ParameterizedTypeReference superclass");
			}
			return findParameterizedTypeReferenceSubClass(parent);
		}
		return genericSuperclass;
	}

}
