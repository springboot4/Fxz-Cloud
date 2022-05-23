package com.fxz.common.sequence.segment.mysql.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fxz.common.sequence.exception.SeqException;
import com.fxz.common.sequence.segment.mysql.entity.SequenceSegment;
import com.fxz.common.sequence.segment.mysql.mapper.SequenceSegmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 13:17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcSegmentService {

	private final SequenceSegmentMapper mapper;

	/**
	 * 不存在就set，存在就忽略
	 */
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public void setIfAbsentRange(String key, Long rangeStart) {
		boolean exists = mapper.exists(Wrappers.<SequenceSegment>lambdaQuery().eq(SequenceSegment::getSegmentKey, key));
		// 是否存在
		if (!exists) {
			mapper.insert(new SequenceSegment().setSegmentKey(key).setSegmentValue(rangeStart));
		}
	}

	/**
	 * 增加号段范围
	 */
	@Transactional(rollbackFor = Exception.class)
	public Long incrementRange(String key, int rangeStep) {
		SequenceSegment segment = mapper
				.selectOne(Wrappers.<SequenceSegment>lambdaQuery().eq(SequenceSegment::getSegmentKey, key));
		if (Objects.isNull(segment)) {
			throw new SeqException("号段不存在");
		}

		// 新号段开始值
		Long stepValue = segment.getSegmentValue() + rangeStep;
		segment.setSegmentValue(stepValue);
		mapper.updateById(segment);

		return stepValue;
	}

}
