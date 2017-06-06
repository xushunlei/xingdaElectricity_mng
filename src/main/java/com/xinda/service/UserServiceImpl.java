package com.xinda.service;

import java.util.List;

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
	@Override
	public List<User> findUserByStatus(Byte status)
	{
		return userDao.selectUsersByStatus(status);
	}
	@Override
	public List<User> findUsers()
	{
		return userDao.selectUsers();
	}
	@Override
	public int getTotalCountOfUser()
	{
		return userDao.selectTotalCount();
	}
	@Override
	public int getCountByStatus(Byte status)
	{
		return userDao.selectCountByStatus(status);
	}
}
