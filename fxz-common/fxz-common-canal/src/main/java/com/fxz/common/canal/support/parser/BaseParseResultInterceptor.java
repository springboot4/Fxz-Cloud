package com.fxz.common.canal.support.parser;

import com.fxz.common.canal.model.ModelTable;
import com.fxz.common.canal.support.BaseParameterizedTypeReferenceSupport;

/**
 * @author fxz
 */
public abstract class BaseParseResultInterceptor<T> extends BaseParameterizedTypeReferenceSupport<T> {

	public BaseParseResultInterceptor() {
		super();
	}

	public void onParse(ModelTable modelTable) {

	}

	public void onBeforeInsertProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onAfterInsertProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onBeforeUpdateProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onAfterUpdateProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onBeforeDeleteProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onAfterDeleteProcess(ModelTable modelTable, T beforeData, T afterData) {

	}

	public void onBeforeDDLProcess(ModelTable modelTable, T beforeData, T afterData, String sql) {

	}

	public void onAfterDDLProcess(ModelTable modelTable, T beforeData, T afterData, String sql) {

	}

	public void onParseFinish(ModelTable modelTable) {

	}

	public void onParseCompletion(ModelTable modelTable) {

	}

}
