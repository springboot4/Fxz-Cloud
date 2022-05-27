package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 18:34
 */
@Data
@TableName("sys_dept")
public class Dept extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7139055608314923987L;

	/**
	 * 部门id
	 */
	@TableId(type = IdType.AUTO)
	private Long deptId;

	/**
	 * 上级部门id
	 */
	private Long parentId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 排序
	 */
	private Double orderNum;

	/**
	 * 子部门id
	 */
	@TableField(exist = false)
	private List<Dept> children;

}
