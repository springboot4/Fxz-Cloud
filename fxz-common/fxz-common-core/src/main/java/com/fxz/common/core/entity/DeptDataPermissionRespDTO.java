package com.fxz.common.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门的数据权限 Response DTO
 *
 * @author fxz
 */
@Data
public class DeptDataPermissionRespDTO implements Serializable {

	/**
	 * 是否可查看全部数据
	 */
	private Boolean all;

	/**
	 * 是否可查看自己的数据
	 */
	private Boolean self;

	/**
	 * 可查看的部门编号数组
	 */
	private Set<Long> deptIds;

	public DeptDataPermissionRespDTO() {
		this.all = false;
		this.self = false;
		this.deptIds = new HashSet<>();
	}

}
