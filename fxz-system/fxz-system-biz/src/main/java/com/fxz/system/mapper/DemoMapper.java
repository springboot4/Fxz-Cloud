package com.fxz.system.mapper;

import com.fxz.system.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-30 10:35
 */
@Mapper
public interface DemoMapper {

	// @DS("#last")
	List<Map<String, String>> selectTest(@Param("tableName") String tableName, String dsName);

	// @DS("#last")
	List<SystemUser> selectUser(@Param("tableName") String tableName, String dsName);

}
