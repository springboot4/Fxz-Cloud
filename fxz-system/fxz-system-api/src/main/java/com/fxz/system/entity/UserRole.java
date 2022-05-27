package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fxz
 */
@Data
@TableName("sys_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -3166012934498268403L;

	@TableField(value = "USER_ID")
	private Long userId;

	@TableField(value = "ROLE_ID")
	private Long roleId;

}