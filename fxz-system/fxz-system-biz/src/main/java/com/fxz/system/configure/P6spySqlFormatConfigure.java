package com.fxz.system.configure;

import com.fxz.common.core.utils.DateUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 自定义日志打印
 *
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 18:17
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
			String sql, String url) {
		return StringUtils.isNotBlank(sql)
				? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN) + " | 耗时 " + elapsed
						+ " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";"
				: StringUtils.EMPTY;
	}

}
