package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-28 18:31
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = -6050162113631919804L;

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 菜单id
	 */
	private Long menuId;

}
