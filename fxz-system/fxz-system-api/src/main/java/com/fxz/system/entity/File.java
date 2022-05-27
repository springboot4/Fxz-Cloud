package com.fxz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文件管理
 *
 * @author fxz
 * @date 2022-04-04
 */
@Data
@TableName("sys_file")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class File extends BaseEntity {

	private static final long serialVersionUID = -1L;

	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private String fileName;

	/**
	 *
	 */
	private String bucketName;

	/**
	 *
	 */
	private String original;

	/**
	 *
	 */
	private String type;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

}
