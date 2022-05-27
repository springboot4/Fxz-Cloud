package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 17:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1189610678764394766L;

	/**
	 * 角色id
	 */
	@TableId(type = IdType.AUTO)
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String remark;

	/**
	 * 数据权限范围
	 */
	private Integer dataScope;

	/**
	 * 数据范围(指定部门数组)
	 */
	private String dataScopeDeptIds;

	/**
	 * 角色拥有的菜单id
	 */
	@TableField(exist = false)
	private String menuId;

}
