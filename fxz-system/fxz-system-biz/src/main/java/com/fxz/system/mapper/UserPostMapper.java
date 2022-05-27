package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.UserPost;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
@Mapper
public interface UserPostMapper extends BaseMapper<UserPost> {

}