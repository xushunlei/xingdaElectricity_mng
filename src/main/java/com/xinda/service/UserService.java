package com.xinda.service;

import com.xinda.entity.User;
import com.xinda.entity.Zone;

public interface UserService {
	/**检查用户是否存在*/
	public boolean checkUserIsExist(User user);
	/**用户登陆*/
	public User login(User user);
	/**根据ID查用户*/
	public User findById(Integer id);
	/**修改用户*/
	public boolean modifyUser(User user);
	public void modifyPwdByIds(String[] ids, String newPWD);
	
	/**查找用户管理区域的ID*/
	public int findZoneidByUserid(Integer userId);
	
	public boolean tx_saveUser(User user,Zone zone);
}
