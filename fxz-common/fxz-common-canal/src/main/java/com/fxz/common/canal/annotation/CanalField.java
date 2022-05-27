package com.fxz.common.canal.annotation;

import com.fxz.common.canal.support.parser.converter.BaseCanalFieldConverter;
import com.fxz.common.canal.support.parser.converter.NullCanalFieldConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

/**
 * @author fxz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CanalField {

	/**
	 * 行名称
	 * @return columnName
	 */
	String columnName() default "";

	/**
	 * sql字段类型
	 * @return JDBCType
	 */
	JDBCType sqlType() default JDBCType.NULL;

	/**
	 * 字段转换器
	 * @return klass
	 */
	Class<? extends BaseCanalFieldConverter<?>> converterKlass() default NullCanalFieldConverter.class;

}
