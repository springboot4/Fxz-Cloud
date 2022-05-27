package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Data
@TableName("sys_dict_item")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DictItem extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 *
	 */
	private Long id;

	/**
	 * 字典ID
	 */
	private Long dictId;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 标签
	 */
	private String label;

	/**
	 * 字典类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 排序（升序）
	 */
	private Integer sortOrder;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

}
