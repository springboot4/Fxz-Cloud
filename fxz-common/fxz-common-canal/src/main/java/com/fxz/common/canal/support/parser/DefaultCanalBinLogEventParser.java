package com.fxz.common.canal.support.parser;

import com.alibaba.fastjson.JSON;
import com.fxz.common.canal.common.BinLogEventType;
import com.fxz.common.canal.common.OperationType;
import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author fxz
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class DefaultCanalBinLogEventParser implements CanalBinLogEventParser {

	@Override
	public <T> List<CanalBinLogResult<T>> parse(CanalBinLogEvent event, Class<T> klass,
			BasePrimaryKeyTupleFunction primaryKeyFunction, BaseCommonEntryFunction<T> commonEntryFunction) {
		BinLogEventType eventType = BinLogEventType.fromType(event.getType());
		if (BinLogEventType.UNKNOWN == eventType || BinLogEventType.QUERY == eventType) {
			System.out.println("监听到不需要处理或者未知的binlog事件类型[{}],将忽略解析过程返回空列表,binlog事件:{}" + eventType + " "
					+ JSON.toJSONString(event));
			return Collections.emptyList();
		}
		if (Boolean.TRUE.equals(event.getIsDdl())) {
			CanalBinLogResult<T> entry = new CanalBinLogResult<>();
			entry.setOperationType(OperationType.DDL);
			entry.setBinLogEventType(eventType);
			entry.setDatabaseName(event.getDatabase());
			entry.setTableName(event.getTable());
			entry.setSql(event.getSql());
			return Collections.singletonList(entry);
		}
		Optional.ofNullable(event.getPkNames()).filter(x -> x.size() == 1)
				.orElseThrow(() -> new IllegalArgumentException("DML类型binlog事件主键列数量不为1"));
		// 主键列的名称
		String primaryKeyName = event.getPkNames().get(0);
		List<CanalBinLogResult<T>> entryList = new LinkedList<>();
		List<Map<String, String>> data = event.getData();
		List<Map<String, String>> old = event.getOld();
		int dataSize = null != data ? data.size() : 0;
		int oldSize = null != old ? old.size() : 0;
		if (dataSize > 0) {
			for (int index = 0; index < dataSize; index++) {
				CanalBinLogResult<T> entry = new CanalBinLogResult<>();
				entryList.add(entry);
				entry.setSql(event.getSql());
				entry.setOperationType(OperationType.DML);
				entry.setBinLogEventType(eventType);
				entry.setTableName(event.getTable());
				entry.setDatabaseName(event.getDatabase());
				Map<String, String> item = data.get(index);
				entry.setAfterData(commonEntryFunction.apply(item));
				// 预防old节点空指针
				Map<String, String> oldItem = null;
				if (oldSize > 0 && index <= oldSize) {
					oldItem = old.get(index);
					entry.setBeforeData(commonEntryFunction.apply(oldItem));
				}
				// 封装主键
				entry.setPrimaryKey(primaryKeyFunction.apply(oldItem, item, primaryKeyName));
			}
		}
		return entryList;
	}

}
