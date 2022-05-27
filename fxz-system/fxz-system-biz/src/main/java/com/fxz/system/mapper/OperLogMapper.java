package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.OperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志记录
 *
 * @author fxz
 * @date 2022-04-07
 */
@Mapper
public interface OperLogMapper extends BaseMapper<OperLog> {

}