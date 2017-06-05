package com.xinda.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xinda.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RoleServiceTest
{
	@Autowired
	private RoleService roleService;
	@Test
	public void addRole(){
		Role role=new Role();
		role.setRoleName("普通用户");
		role.setRoleStatus(0);
		roleService.saveRole(role);
	}
}
