package com.xinda.dao;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.User;

public interface UserMapper {
	/**根据用户名和密码查找管理员数量*/
	int selectCountByUser(User user);
	/**根据用户名和密码查找管理员*/
	User selectByUser(User user);
	/**根据id查找管理员*/
	User selectUserById(Integer id);
	/**根据id查找对应用户管理的区域id*/
	int selectUserZoneId(Integer userid);
	/**修改管理员信息*/
	int updateByUser(User user);
	/**根据id更改密码*/
	void updatePwdById(@Param("password")String password, @Param("userId")int userId);
	/**新建一个管理员*/
	int insert(User user);
}