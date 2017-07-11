package com.xinda.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinda.entity.User;
import com.xinda.service.BranchService;
import com.xinda.service.UserService;
@Controller
@RequestMapping("user/")
public class UserController
{
	@Autowired
	private UserService userService;
	@Autowired
	private BranchService branchService;
	/**用户注册*/
	@RequestMapping(value="registed",method=RequestMethod.POST)
	public String registed(HttpServletRequest request,HttpServletResponse response){
		User user=(User) request.getAttribute("loginUser");
		if(userService.checkUserCanRegisted(user)&&userService.saveUser(user)){
			return "main";
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
		user.setUserAccount(nameString);
		user.setUserPassword(password);
		if(userService.checkUserIsExist(user)){
			user=userService.login(user);
			/*if(user.getRole()==1){//管理员
				List<User> userList=userService.findUsers();
				session.setAttribute("userList", userList);
				//session.setAttribute("branchList", branchService.getBranchs());
			}else{
				
			}*/
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
	/**查看用户报表*/
	@RequestMapping("tablesView")
	public String showTables(HttpServletRequest request, HttpServletResponse response){
		return "tables";
	}
	/**查看用户个人资料*/
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
	/**查看用户列表*/
	@RequestMapping("userlistView")
	public String showUserlist(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null&&loginUser.getUserRole()==1){
			session.setAttribute("userList", userService.findUsers());
			return "userlist";
		}else{
			return "index";
		}
	}
	/**编辑用户资料*/
	@RequestMapping("editprofile")
	public String editFrofile(){
		return "userinfo";
	}
	/**修改用户资料*/
	//@Transactional//事务性。程序出错会回滚
	@ResponseBody
	@RequestMapping("saveUser")
	public Map<String, Object> saveUser(User user,HttpServletRequest request){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		boolean flag=false;
		if(user.getUserId()!=null&&userService.modifyUser(user)){
			flag=true;
			request.getSession().setAttribute("loginUser", userService.findById(user.getUserId()));
		}
		resultMap.put("flag", flag);
		return resultMap;
	}
	/**修改用户密码*/
	@ResponseBody
	@RequestMapping(value="modifyPwd",method=RequestMethod.POST)
	public Map<String,Boolean> modifyPwd(HttpServletRequest request){
		Map<String, Boolean> resultMap=new HashMap<String, Boolean>();
		boolean flag=false;
		//String account=(String) request.getAttribute("account");
		String password=request.getParameter("password");
		User user=(User) request.getSession().getAttribute("loginUser");
		if(user!=null){
			user.setUserPassword(password);
			flag=userService.modifyUser(user);
		}
		//user.setAccount(account);
		resultMap.put("flag", flag);
		return resultMap;
	}
	@RequestMapping("jumpin_mnguser")
	public String jumpMnguser(HttpServletRequest request){
		request.getSession().setAttribute("branchs", branchService.findAllBranch());
		return "mng-user";
	}
	@RequestMapping("jumpin_modifyprice")
	public String jumpModify(){
		return "mng-modifyprice";
	}
	@RequestMapping("jumpin_addbranch")
	public String jumpAddb(){
		return "mng-addbranch";
	}
	@RequestMapping("jumpin_adduser")
	public String jumpAddu(){
		return "mng-adduser";
	}
	@RequestMapping("jumpin_sendmsg")
	public String jumpSendmsg(){
		return "mng-sendmsg";
	}
	@RequestMapping("jumpin_dosagePage")
	public String dosagePage(){
		return "histroy-dosage";
	}
	@RequestMapping("jumpin_pricePage")
	public String pricePage(){
		return "histroy-price";
	}
	@RequestMapping("jumpin_rechargePage")
	public String rechargePage(HttpServletRequest request){
		request.getSession().setAttribute("branchList", branchService.findAllBranch());
		return "histroy-recharge";
	}
}
