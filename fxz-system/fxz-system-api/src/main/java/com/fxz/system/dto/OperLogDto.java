package com.fxz.system.dto;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class OperLogDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String title;

	private Integer businessType;

	private String method;

	private String requestMethod;

	private String operName;

	private String operUrl;

	private String operIp;

	private String operParam;

	private Integer status;

	private String errorMsg;

	private Long time;

}