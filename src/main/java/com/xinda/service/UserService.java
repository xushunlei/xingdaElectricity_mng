package com.xinda.service;

import java.util.List;

import com.xinda.entity.User;

public interface UserService
{
	/**检查用户是否可注册*/
	public boolean checkUserCanRegisted(User user);
	/**保存注册用户*/
	public boolean saveUser(User user);
	/**检查用户是否存在*/
	public boolean checkUserIsExist(User user);
	/**用户登陆*/
	public User login(User user);
	/**根据状态查用户列表*/
	public List<User> findUserByStatus(Byte status);
	/**查寻用户列表*/
	public List<User> findUsers();
	/**查询所有用户数量*/
	public int getTotalCountOfUser();
	/**查询指定状态用户数量*/
	public int getCountByStatus(Byte status);
}
