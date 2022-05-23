package com.fxz.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author fxz
 */
@Accessors(chain = true)
@Data
public class UserInfo implements Serializable {

	/**
	 * 用户基本信息
	 */
	private SystemUser sysUser;

	/**
	 * 权限标识集合
	 */
	private List<String> permissions;

}
