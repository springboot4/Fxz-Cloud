package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.canal.annotation.CanalModel;
import com.fxz.common.canal.common.FieldNamingPolicy;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 操作日志记录
 *
 * @author fxz
 * @date 2022-04-07
 */
@Data
@TableName("sys_oper_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@CanalModel(database = "fxz_cloud_base", table = "sys_oper_log", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
public class OperLog extends BaseEntity {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 模块标题
	 */
	private String title;

	/**
	 * 业务类型
	 */
	private Integer businessType;

	/**
	 * 方法名称
	 */
	private String method;

	/**
	 * 请求方式
	 */
	private String requestMethod;

	/**
	 * 操作人员
	 */
	private String operName;

	/**
	 * 请求URL
	 */
	private String operUrl;

	/**
	 * 主机地址
	 */
	private String operIp;

	/**
	 * 请求参数
	 */
	private String operParam;

	/**
	 * 操作状态（0正常 1异常）
	 */
	private Integer status;

	/**
	 * 错误消息
	 */
	private String errorMsg;

	/**
	 * 执行时间
	 */
	private Long time;

}
