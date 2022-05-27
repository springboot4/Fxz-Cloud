package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
@Data
@TableName("sys_user_post")
@Accessors(chain = true)
public class UserPost {

	private static final long serialVersionUID = -1L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 岗位ID
	 */
	private Long postId;

}
