package com.fxz.common.canal.support.parser.converter;

import com.alibaba.fastjson.JSON;

import java.sql.SQLType;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fxz
 */
@SuppressWarnings("rawtypes")
public class InMemoryCanalFieldConverterFactory implements CanalFieldConverterFactory {

	private final Set<BaseCanalFieldConverter> converters = new HashSet<>(16);

	private final ConcurrentMap<Class<?>, BaseCanalFieldConverter> typeKlassConverters = new ConcurrentHashMap<>(16);

	private final ConcurrentMap<SQLType, BaseCanalFieldConverter> sqlTypeConverters = new ConcurrentHashMap<>(16);

	private final ConcurrentMap<Class<? extends BaseCanalFieldConverter>, BaseCanalFieldConverter> klassConverters = new ConcurrentHashMap<>(
			16);

	public static CanalFieldConverterFactory of() {
		return new InMemoryCanalFieldConverterFactory();
	}

	private InMemoryCanalFieldConverterFactory() {
		loadInternalConverters();
	}

	private void loadInternalConverters() {
		converters.add(NullCanalFieldConverter.X);
		converters.add(BigIntCanalFieldConverter.X);
		converters.add(VarcharCanalFieldConverter.X);
		converters.add(IntCanalFieldConverter.X);
		converters.add(DecimalCanalFieldConverter.X);
		converters.add(TinyIntCanalFieldConverter.X);
		converters.add(TimestampCanalFieldConverter0.X);
		converters.add(SqlDateCanalFieldConverter0.X);
		converters.add(TimestampCanalFieldConverter1.X);
		converters.add(SqlDateCanalFieldConverter1.X);
		converters.add(TimestampCanalFieldConverter2.X);
		converters.forEach(converter -> registerConverter(converter, true));
	}

	@Override
	public void registerConverter(BaseCanalFieldConverter<?> converter, boolean replace) {
		if (replace) {
			typeKlassConverters.put(converter.typeKlass(), converter);
			sqlTypeConverters.put(converter.sqlType(), converter);
			klassConverters.put(converter.getClass(), converter);
		}
		else {
			typeKlassConverters.putIfAbsent(converter.typeKlass(), converter);
			sqlTypeConverters.putIfAbsent(converter.sqlType(), converter);
			klassConverters.putIfAbsent(converter.getClass(), converter);
		}
	}

	@Override
	public CanalFieldConvertResult load(CanalFieldConvertInput input) {
		BaseCanalFieldConverter<?> converter;
		if (null != input.getSqlType() && null != (converter = sqlTypeConverters.get(input.getSqlType()))) {
			return CanalFieldConvertResult.builder().converter(converter).build();
		}
		if (null != input.getConverterKlass() && null != (converter = klassConverters.get(input.getConverterKlass()))) {
			return CanalFieldConvertResult.builder().converter(converter).build();
		}
		if (null != input.getFieldKlass() && null != (converter = typeKlassConverters.get(input.getFieldKlass()))) {
			return CanalFieldConvertResult.builder().converter(converter).build();
		}
		throw new IllegalArgumentException(String.format("加载Canal类型转换器失败,输入参数:%s", JSON.toJSONString(input)));
	}

}
