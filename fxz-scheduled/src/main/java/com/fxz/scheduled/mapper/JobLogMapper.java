package com.fxz.scheduled.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.scheduled.entity.JobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务调度日志表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Mapper
public interface JobLogMapper extends BaseMapper<JobLog> {

}