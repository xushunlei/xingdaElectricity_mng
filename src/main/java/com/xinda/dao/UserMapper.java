package com.xinda.dao;

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

}