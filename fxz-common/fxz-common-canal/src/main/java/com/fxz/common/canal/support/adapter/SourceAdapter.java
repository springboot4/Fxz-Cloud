package com.fxz.common.canal.support.adapter;

/**
 * @author fxz
 */
public interface SourceAdapter<SOURCE, SINK> {

	SINK adapt(SOURCE source);

}
