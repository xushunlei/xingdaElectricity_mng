package com.xinda.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("enable")
	public void enableUser(HttpServletRequest request,HttpServletResponse response){
		String[] idStrings=request.getParameter("userid").split(",");
		userService.editStatusById(idStrings, (byte)0);
		try
		{
			request.getRequestDispatcher("manageView").forward(request, response);
		} catch (ServletException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "userlist";
	}
	@RequestMapping("disable")
	public void disableUser(HttpServletRequest request,HttpServletResponse response){
		String[] idStrings=request.getParameter("userid").split(",");
		userService.editStatusById(idStrings, (byte)1);
		try
		{
			request.getRequestDispatcher("manageView").forward(request, response);
		} catch (ServletException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value="modifyPwd",method=RequestMethod.POST)
	public Map<String, Boolean> modifyPwd(HttpServletRequest request){
		Map<String, Boolean> result=new HashMap<String, Boolean>();
		boolean flag=false;
		String[] idStrings=request.getParameter("ids").split(",");
		userService.modifyPwdByIds(idStrings, "000000");
		result.put("flag", flag);
		return result;
	}
}
