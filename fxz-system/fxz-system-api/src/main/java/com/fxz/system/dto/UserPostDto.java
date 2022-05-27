package com.fxz.system.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserPostDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long userId;

	private Long postId;

}