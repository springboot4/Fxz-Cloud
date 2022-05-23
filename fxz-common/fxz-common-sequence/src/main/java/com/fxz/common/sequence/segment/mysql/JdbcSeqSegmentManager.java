package com.fxz.common.sequence.segment.mysql;

import com.fxz.common.sequence.exception.SeqException;
import com.fxz.common.sequence.segment.SeqSegment;
import com.fxz.common.sequence.segment.SeqSegmentConfig;
import com.fxz.common.sequence.segment.SeqSegmentManager;
import com.fxz.common.sequence.segment.mysql.service.JdbcSegmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mysql号段管理器
 *
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 13:14
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "fxz.common.sequence", value = "type", havingValue = "mysql", matchIfMissing = true)
public class JdbcSeqSegmentManager implements SeqSegmentManager {

	private final JdbcSegmentService jdbcSegmentService;

	private static Map<String, SeqSegment> seqRangeMap = new ConcurrentHashMap<>(8);

	/**
	 * 获取指定号段名的下一个号段
	 * @param name 号段名
	 * @param seqSegmentConfig 序列号号段配置
	 * @return 返回号段
	 * @throws SeqException 序列号异常
	 */
	@Override
	public SeqSegment nextSegment(String name, SeqSegmentConfig seqSegmentConfig) throws SeqException {
		SeqSegment seqSegment = seqRangeMap.get(name);
		if (Objects.isNull(seqSegment)) {
			// 不存在就set，存在就忽略
			jdbcSegmentService.setIfAbsentRange(name, seqSegmentConfig.getRangeStart());
		}

		Integer rangeStep = seqSegmentConfig.getRangeStep();
		Long max = jdbcSegmentService.incrementRange(name, rangeStep);
		Long min = max - rangeStep;

		seqSegment = new SeqSegment(min, max, seqSegmentConfig.getStep());

		seqRangeMap.put(name, seqSegment);

		return seqSegment;
	}

}
