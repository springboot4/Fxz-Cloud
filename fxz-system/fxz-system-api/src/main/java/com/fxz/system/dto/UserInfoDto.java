package com.fxz.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 12:25
 */
@Data
public class UserInfoDto implements Serializable {

	private static final long serialVersionUID = -6220014292300727768L;

	/**
	 * 用户 ID
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 联系电话
	 */
	private String mobile;

	/**
	 * 状态 0锁定 1有效
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 性别 0男 1女 2 保密
	 */
	private String sex;

}
