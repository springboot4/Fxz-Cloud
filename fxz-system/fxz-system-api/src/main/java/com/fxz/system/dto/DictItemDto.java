package com.fxz.system.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 字典项
 *
 * @author fxz
 * @date 2022-04-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DictItemDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long dictId;

	private String value;

	private String label;

	private String type;

	private String description;

	private Integer sortOrder;

	private String remark;

	private String delFlag;

}