package com.fxz.system.configure;

import com.fxz.common.sequence.segment.SeqSegmentConfig;
import com.fxz.common.sequence.segment.SeqSegmentManager;
import com.fxz.common.sequence.service.Sequence;
import com.fxz.common.sequence.service.impl.DefaultSegmentSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/23 14:29
 */
@Configuration
@RequiredArgsConstructor
public class SeqConfig {

	private final SeqSegmentManager seqSegmentManager;

	@Bean
	public Sequence fxzSequence() {
		// 序列号号段配置
		SeqSegmentConfig seqSegmentConfig = new SeqSegmentConfig().setStep(1).setRangeStart(0L).setRangeStep(10);

		return new DefaultSegmentSequence(seqSegmentManager, seqSegmentConfig);
	}

	@Bean
	public Sequence cloudSequence() {
		// 序列号号段配置
		SeqSegmentConfig seqSegmentConfig = new SeqSegmentConfig().setStep(1).setRangeStart(0L).setRangeStep(1000);

		return new DefaultSegmentSequence(seqSegmentManager, seqSegmentConfig);
	}

}
