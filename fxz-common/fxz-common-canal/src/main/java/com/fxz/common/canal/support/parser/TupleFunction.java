package com.fxz.common.canal.support.parser;

/**
 * @author fxz
 */
@FunctionalInterface
public interface TupleFunction<BEFORE, AFTER, KEY, R> {

	R apply(BEFORE before, AFTER after, KEY key);

}
