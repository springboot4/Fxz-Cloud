package com.fxz.common.canal.support.parser;

import java.util.List;

/**
 * @author fxz
 */
public interface ParseResultInterceptorManager {

	<T> void registerParseResultInterceptor(BaseParseResultInterceptor<T> parseResultInterceptor);

	<T> List<BaseParseResultInterceptor<T>> getParseResultInterceptors(Class<T> klass);

}
