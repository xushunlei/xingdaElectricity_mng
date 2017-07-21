package com.xinda.controller;

import gnu.io.SerialPort;

import java.awt.AWTException;
import java.awt.Robot;
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

import com.xinda.entity.HistoricalPrice;
import com.xinda.entity.User;
import com.xinda.service.BranchService;
import com.xinda.service.PriceService;
import com.xinda.service.UserService;
import com.xinda.util.SerialTool;
@Controller
@RequestMapping("user/")
public class UserController
{
	@Autowired
	private UserService userService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private PriceService priceService;
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
	public String jumpModify(HttpServletRequest request){
		HttpSession session=request.getSession();
		HistoricalPrice currPrice=priceService.findPriceByActive(new Byte("1"));
		session.setAttribute("currPrice", currPrice);
		HistoricalPrice futurePrice=priceService.findPriceByActive(new Byte("0"));
		if(futurePrice==null){
			futurePrice=currPrice;
		}
		session.setAttribute("futurePrice", futurePrice);
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
	@ResponseBody
	@RequestMapping("duqu")
	public Map<String,String> duqu(){
		SerialTool st=new SerialTool();
		byte[] command1={(byte)0xFE,(byte)0x68,(byte)0xCC,(byte)0x68,(byte)0x37,(byte)0x82,(byte)0x11};
		st.scanPorts();
		st.openSerialPort("COM3");
		st.setSeriaPortParam(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		st.sendDataToSeriaPort(command1);
		/*Timer timer = new Timer();// 实例化Timer类  
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("退出");  
                this.cancel();  
            }  
        }, 5000);// 这里百毫秒  
        System.out.println("本程序存在5秒后自动退出");*/ 
		try {
			Robot r=new Robot();
			r.delay(5000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		String redata=st.getReceive();
		st.closeSerialPort();
		System.out.println(":::>"+redata);
		Map<String,String> reMap=new HashMap<String,String>();
		reMap.put("data", redata);
		return reMap;
	}
}
