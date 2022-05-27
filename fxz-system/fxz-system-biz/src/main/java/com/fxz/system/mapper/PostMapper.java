package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.Post;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位信息表
 *
 * @author fxz
 * @date 2022-04-05
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}