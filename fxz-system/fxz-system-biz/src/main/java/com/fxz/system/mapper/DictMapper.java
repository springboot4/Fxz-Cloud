package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 *
 * @author fxz
 * @date 2022-04-04
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}