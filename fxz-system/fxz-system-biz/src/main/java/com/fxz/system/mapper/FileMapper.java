package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件管理表
 *
 * @author fxz
 * @date 2022-04-04
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

}