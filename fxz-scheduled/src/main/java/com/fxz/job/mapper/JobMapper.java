package com.fxz.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.job.entity.SysJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务调度
 *
 * @author fxz
 * @date 2022-04-03
 */
@Mapper
public interface JobMapper extends BaseMapper<SysJob> {

}