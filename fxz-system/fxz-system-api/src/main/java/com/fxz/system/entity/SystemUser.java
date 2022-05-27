package com.fxz.system.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fxz.common.canal.annotation.CanalModel;
import com.fxz.common.canal.common.FieldNamingPolicy;
import com.fxz.common.core.annotation.CheckMobileValid;
import com.fxz.common.core.enums.SensitiveType;
import com.fxz.common.core.sensitive.SensitiveInfo;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 15:22
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
@CanalModel(database = "fxz_cloud_base", table = "sys_user", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
public class SystemUser extends BaseEntity implements Serializable {

	/**
	 * 用户状态：有效
	 */
	public static final String STATUS_VALID = "1";

	/**
	 * 用户状态：锁定
	 */
	public static final String STATUS_LOCK = "0";

	/**
	 * 默认头像
	 */
	public static final String DEFAULT_AVATAR = "";

	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 性别男
	 */
	public static final String SEX_MALE = "0";

	/**
	 * 性别女
	 */
	public static final String SEX_FEMALE = "1";

	/**
	 * 性别保密
	 */
	public static final String SEX_UNKNOW = "2";

	/**
	 * 用户 ID
	 */
	@TableId(type = IdType.AUTO)
	private Long userId;

	/**
	 * 用户名
	 */
	@Size(min = 3, max = 10, message = "{range}")
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 部门 ID
	 */
	private Long deptId;

	/**
	 * 邮箱
	 */
	@SensitiveInfo(SensitiveType.EMAIL)
	@Email(message = "{email}")
	private String email;

	/**
	 * 联系电话
	 */
	@SensitiveInfo(SensitiveType.MOBILE_PHONE)
	@CheckMobileValid(message = "{mobile}")
	private String mobile;

	/**
	 * 状态 0锁定 1有效
	 */
	@NotBlank(message = "{required}")
	private String status;

	/**
	 * 最近访问时间
	 */
	@DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date lastLoginTime;

	/**
	 * 性别 0男 1女 2 保密
	 */
	@TableField(value = "ssex")
	@NotBlank(message = "{required}")
	private String sex;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 描述
	 */
	@Size(max = 100, message = "{noMoreThan}")
	private String description;

	/**
	 * 部门名称
	 */
	@TableField(exist = false)
	private String deptName;

	@TableField(exist = false)
	private String createTimeFrom;

	@TableField(exist = false)
	private String createTimeTo;

	/**
	 * 岗位 ID
	 */
	@TableField(exist = false)
	private String postId;

	@TableField(exist = false)
	private String postName;

	/**
	 * 角色 ID
	 */
	@TableField(exist = false)
	private String roleId;

	@TableField(exist = false)
	private String roleName;

}
