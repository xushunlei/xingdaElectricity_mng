package com.xinda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.RoleMapper;
import com.xinda.entity.Role;
@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleMapper roleDao;
	@Override
	public boolean saveRole(Role role)
	{
		if(roleDao.insert(role)==1){
			return true;
		}else{
			return false;
		}
	}

}
