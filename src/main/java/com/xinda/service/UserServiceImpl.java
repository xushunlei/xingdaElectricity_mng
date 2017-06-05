package com.xinda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.UserMapper;
import com.xinda.entity.User;
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userDao;
	@Override
	public boolean checkUserCanRegisted(User user)
	{
		if(null==user||null==user.getAccount()){
			return false;
		}
		int count=userDao.selectCountByAccount(user.getAccount());
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean saveUser(User user)
	{
		if(userDao.insertSelective(user)==1){
			return true;
		}{
			return false;
		}
	}
	@Override
	public boolean checkUserIsExist(User user)
	{
		if(userDao.selectCountByUser(user)==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public User login(User user)
	{
		return userDao.selectByUser(user);
		
	}
}
