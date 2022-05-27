package com.fxz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.system.dto.OperLogDto;
import com.fxz.system.entity.OperLog;

import java.util.List;

/**
 * 操作日志记录
 *
 * @author fxz
 * @date 2022-04-07
 */
public interface OperLogService extends IService<OperLog> {

	/**
	 * 添加
	 */
	Boolean addOperLog(OperLogDto operLogDto);

	/**
	 * 修改
	 */
	Boolean updateOperLog(OperLogDto operLogDto);

	/**
	 * 分页
	 */
	IPage<OperLog> pageOperLog(Page<OperLog> pageParam, OperLog operLog);

	/**
	 * 获取单条
	 */
	OperLog findById(Long id);

	/**
	 * 获取全部
	 */
	List<OperLog> findAll();

	/**
	 * 删除
	 */
	Boolean deleteOperLog(Long id);

}