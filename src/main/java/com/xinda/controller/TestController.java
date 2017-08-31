package com.xinda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinda.entity.User;
import com.xinda.service.UserService;

@Controller
@RequestMapping("/")
public class TestController
{
	@Autowired
	private UserService userService;
	@RequestMapping
	public String Hello(){
		return "index";
	}
	/**用户登陆*/
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String nameString=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		user.setUserAccount(nameString);
		user.setUserPassword(password);
		if(userService.checkUserIsExist(user)){
			user=userService.login(user);
			session.setAttribute("loginUser", user);
			return "main";
		}else{
			return "index";
		}
		
	}
	/**用户退出*/
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("loginUser");
		return "index";
	}
}
