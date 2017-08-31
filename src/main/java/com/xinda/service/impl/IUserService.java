package com.xinda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinda.dao.UserMapper;
import com.xinda.dao.ZoneMapper;
import com.xinda.entity.User;
import com.xinda.entity.Zone;
import com.xinda.service.UserService;
@Service
public class IUserService implements UserService {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private ZoneMapper zoneDao;
	@Override
	public boolean checkUserIsExist(User user) {
		return userDao.selectCountByUser(user)!=0;
	}

	@Override
	public User login(User user) {
		return userDao.selectByUser(user);
	}

	@Override
	public User findById(Integer id) {
		return userDao.selectUserById(id);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.updateByUser(user)==1;
	}

	@Override
	public void modifyPwdByIds(String[] ids, String password) {
		for(String id:ids){
			if(!id.isEmpty())
				userDao.updatePwdById(password, Integer.parseInt(id));
		}
	}
	@Override
	public int findZoneidByUserid(Integer userId) {
		return userDao.selectUserZoneId(userId);
	}
	@Override
	@Transactional
	public boolean tx_saveUser(User user,Zone zone) {
		if(user.getUserCord()!=null&&user.getUserCord().trim()=="")user.setUserCord(null);
		if(zone.getZoneDescription()!=null&&zone.getZoneDescription().trim()=="")zone.setZoneDescription(null);
		if(zoneDao.insertZone(zone)==1){
			user.setUserZone(zone);
			if(user.getUserPassword()==null||user.getUserPassword().trim()=="")
				user.setUserPassword("000000");//默认密码
			user.setUserRole(0);//默认是普通管理员
			return userDao.insert(user)==1;
		}
		return false;
	}
}
