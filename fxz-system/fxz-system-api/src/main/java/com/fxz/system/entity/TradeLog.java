package com.fxz.system.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_trade_log")
public class TradeLog implements Serializable {

	private static final long serialVersionUID = 3902838426348137002L;

	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	@TableField("GOODS_ID")
	private String goodsId;

	@TableField("GOODS_NAME")
	private String goodsName;

	@TableField("STATUS")
	private String status;

	@DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	@TableField("CREATE_TIME")
	private Date createTime;

}