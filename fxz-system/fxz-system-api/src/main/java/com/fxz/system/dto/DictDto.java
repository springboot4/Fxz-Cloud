package com.fxz.system.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 字典表
 *
 * @author fxz
 * @date 2022-04-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DictDto extends BaseEntity {

	private static final long serialVersionUID = -7062810411163801969L;

	private Long id;

	private String type;

	private String description;

	private String remark;

	private String systemFlag;

	private String delFlag;

}