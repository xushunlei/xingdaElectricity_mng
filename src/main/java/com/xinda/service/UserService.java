package com.xinda.service;

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
}
