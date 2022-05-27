package com.fxz.common.canal.support.adapter;

import com.alibaba.fastjson.JSON;
import com.fxz.common.canal.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author fxz
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
class FastJsonSourceAdapter<T> implements SourceAdapter<String, T> {

	private final Class<T> klass;

	@Override
	public T adapt(String source) {
		if (StringUtils.X.isEmpty(source)) {
			return null;
		}
		return JSON.parseObject(source, klass);
	}

}
