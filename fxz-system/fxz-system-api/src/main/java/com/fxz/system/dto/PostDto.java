package com.fxz.system.dto;

import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 岗位信息表
 *
 * @author fxz
 * @date 2022-04-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PostDto extends BaseEntity {

	private static final long serialVersionUID = -1L;

	private Long postId;

	private String postCode;

	private String postName;

	private Integer postSort;

	private String delFlag;

	private String description;

}