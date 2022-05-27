package com.fxz.system.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fxz.common.mp.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 15:24
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@TableName("sys_menu")
public class Menu extends BaseEntity implements Serializable {

	/**
	 * 一级菜单id
	 */
	public static final long LEVEL_ONE_MENU = 0L;

	/**
	 * 菜单
	 */
	public static final String TYPE_MENU = "0";

	/**
	 * 按钮
	 */
	public static final String TYPE_BUTTON = "1";

	/**
	 * 菜单/按钮ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 上级菜单ID
	 */
	private Long parentId;

	/**
	 * 菜单/按钮名称
	 */
	private String name;

	/**
	 * 菜单URL
	 */
	private String path;

	/**
	 * 重定向地址
	 */
	private String redirect;

	/**
	 * 对应 Vue组件
	 */
	private String component;

	/**
	 * 权限标识
	 */
	private String perms;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 类型 0菜单 1按钮
	 */
	private String type;

	/**
	 * 是否缓存
	 */
	private Integer keepAlive;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 是否隐藏(1 隐藏 0 不隐藏)
	 */
	private String hidden;

	/**
	 * title
	 */
	private String title;

	private transient String createTimeFrom;

	private transient String createTimeTo;

}
