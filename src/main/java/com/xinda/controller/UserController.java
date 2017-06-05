package com.xinda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.xinda.entity.User;
import com.xinda.service.UserService;
@Controller
@RequestMapping("user/")
public class UserController
{
	@Autowired
	private UserService userService;
	
	/**用户注册*/
	@RequestMapping(value="registed",method=RequestMethod.POST)
	public String registed(HttpServletRequest request,HttpServletResponse response){
		User user=(User) request.getAttribute("loginUser");
		if(userService.checkUserCanRegisted(user)&&userService.saveUser(user)){
			return "dashboard";
		}else{
			return "index";
		}
	}
	/**用户登陆*/
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String nameString=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		user.setAccount(nameString);
		user.setPassword(password);
		if(userService.checkUserIsExist(user)){
			user=userService.login(user);
			if(user.getRole()==1){//管理员
				
			}else{
				
			}
			session.setAttribute("loginUser", user);
			return "dashboard";
		}else{
			return "index";
		}
		
	}
	/**查看用户报表*/
	@RequestMapping("tablesView")
	public String showTables(HttpServletRequest request, HttpServletResponse response){
		return "tables";
	}
	/**查看用户资料*/
	@RequestMapping("profileView")
	public String showProfile(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null){
			System.out.println(loginUser);
			return "profile";
		}else {
			return "index";
		}
	}
	
}
