package com.xinda.dao;

import com.xinda.entity.Role;

public interface RoleMapper {
    /**插入新角色*/
	int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}