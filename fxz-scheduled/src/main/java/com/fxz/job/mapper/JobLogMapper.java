package com.fxz.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.job.entity.JobLog;
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