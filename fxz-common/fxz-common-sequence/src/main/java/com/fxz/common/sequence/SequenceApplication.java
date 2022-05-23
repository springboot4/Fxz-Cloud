package com.fxz.common.sequence;

import com.fxz.common.sequence.config.SequenceProperties;
import com.fxz.common.sequence.segment.SeqSegmentConfig;
import com.fxz.common.sequence.segment.SeqSegmentManager;
import com.fxz.common.sequence.service.Sequence;
import com.fxz.common.sequence.service.impl.DefaultSegmentSequence;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/22 20:45
 */
@ComponentScan
@RequiredArgsConstructor
@MapperScan(annotationClass = Mapper.class)
@EnableConfigurationProperties(SequenceProperties.class)
public class SequenceApplication {

	private final SequenceProperties sequenceProperties;

	@Bean
	@ConditionalOnMissingBean(Sequence.class)
	public Sequence sequence(SeqSegmentManager seqSegmentManager) {
		// 序列号号段配置
		SeqSegmentConfig seqSegmentConfig = new SeqSegmentConfig().setStep(sequenceProperties.getStep())
				.setRangeStart(sequenceProperties.getRangeStart()).setRangeStep(sequenceProperties.getRangeStep());

		// 序列号号段生成器接口默认实现
		return new DefaultSegmentSequence(seqSegmentManager, seqSegmentConfig);
	}

}
