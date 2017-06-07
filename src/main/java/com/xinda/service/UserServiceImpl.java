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
		return userDao.selectCountByAccount(user.getAccount())==0;
	}
	@Override
	public boolean saveUser(User user)
	{
		return userDao.insertSelective(user)==1;
	}
	@Override
	public boolean checkUserIsExist(User user)
	{
		return userDao.selectCountByUser(user)!=0;
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
	@Override
	public boolean modifyUser(User user)
	{
		System.out.println(user.getAddress());
		return userDao.updateByUser(user)==1;
	}
	@Override
	public User findById(Integer id)
	{
		return userDao.selectUserById(id);
	}
	@Override
	public void editStatusById(String[] ids,Byte status)
	{
		for(String id:ids){
			if(!id.isEmpty())
				userDao.updateStatus(status, Integer.parseInt(id));
		}
	}
}
