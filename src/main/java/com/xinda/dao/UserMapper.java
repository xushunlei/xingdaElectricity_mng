package com.xinda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.User;

public interface UserMapper {
	/**根据用户名密码查找用户*/
	User selectByUser(User user);
	/**根据用户名查找用户*/
	int selectCountByAccount(String Account);
	/**根据用户查找用户*/
	int selectCountByUser(User user);
	/**插入新用户*/
	int insertSelective(User record);
	/**根据用户状态查找所有用户*/
	List<User> selectUsersByStatus(Byte status);
	/**查找所有用户*/
	List<User> selectUsers();
	/**查询所有用户数量*/
	int selectTotalCount();
	/**查询指定状态用户数量*/
	int selectCountByStatus(Byte status);
	/**修改用户信息*/
	int updateByUser(User user);
	/**根据ID查用户*/
	User selectUserById(Integer id);
	/**修改用户状态*/
	int updateStatus(@Param("st")Byte status,@Param("id")Integer id);
	/**修改密码*/
	int updatePwdById(@Param("pwd")String password,@Param("id")Integer id);
	
}