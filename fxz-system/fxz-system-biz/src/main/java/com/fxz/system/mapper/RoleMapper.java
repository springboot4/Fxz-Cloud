package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 17:52
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据id获取角色信息
	 */
	Role getRoleById(Long id);

}
