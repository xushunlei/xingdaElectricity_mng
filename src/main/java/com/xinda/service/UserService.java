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
	/**修改用户*/
	public boolean modifyUser(User user);
	/**根据ID查用户*/
	public User findById(Integer id);
	/**修改用户状态*/
	public void editStatusById(String[] ids,Byte status);
	/**修改密码*/
	public void modifyPwdByIds(String[] ids,String password);
}
