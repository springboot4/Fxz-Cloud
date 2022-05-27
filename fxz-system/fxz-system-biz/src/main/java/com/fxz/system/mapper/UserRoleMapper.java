package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.system.entity.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author fxz
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户 ID
	 * @return boolean
	 */
	Boolean deleteByUserId(@Param("userId") Long userId);

	/**
	 * 根据角色Id删除该角色的用户关系
	 * @param roleId 角色 ID
	 * @return boolean
	 */
	Boolean deleteByRoleId(@Param("roleId") Long roleId);

}