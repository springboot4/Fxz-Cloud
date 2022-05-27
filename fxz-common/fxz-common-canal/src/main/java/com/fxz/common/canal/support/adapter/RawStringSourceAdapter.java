package com.fxz.common.canal.support.adapter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author fxz
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
class RawStringSourceAdapter implements SourceAdapter<String, String> {

	@Override
	public String adapt(String source) {
		return source;
	}

}
