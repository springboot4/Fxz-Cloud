package com.fxz.common.sequence.segment.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 队列号段
 *
 * @author fxz
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_sequence_segment")
public class SequenceSegment extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 号段key
	 */
	private String segmentKey;

	/**
	 * 号段开始值
	 */
	private Long segmentValue;

}