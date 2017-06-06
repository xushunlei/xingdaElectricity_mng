package com.xinda.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinda.entity.User;
import com.xinda.service.UserService;

@Controller
@RequestMapping("admin/")
public class AdminController
{
	@Autowired
	private UserService userService;
	@RequestMapping("manageView")
	public String managePage(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null&&loginUser.getRole()==1){
			session.setAttribute("userList", userService.findUsers());
			session.setAttribute("paging_user_totalcount", userService.getTotalCountOfUser());
			return "userlist";
		}else{
			return "index";
		}
	}
}
