package com.xinda.controller;

import gnu.io.SerialPort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	public Map<String,String> duqu(HttpServletRequest request){
		String addrStr=request.getParameter("addr");
		byte[] addr=new byte[]{(byte)Long.parseLong(addrStr, 16)};
		SerialTool st=new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		st.setSeriaPortParam(1200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
		st.sendDataToSeriaPort(SerialTool.getValueCommand(addr));
//		st.sendDataToSeriaPort(ConstantPool.READ_CONTACT_ADDRESS);
		/*Timer timer = new Timer();// 实例化Timer类  
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("退出");  
                this.cancel();  
            }  
        }, 5000);// 这里百毫秒  
        System.out.println("本程序存在5秒后自动退出");*/ 
		/*try {
			Robot r=new Robot();
			r.delay(5000);
		} catch (AWTException e) {
			e.printStackTrace();
		}*/
		String redata=st.getReceive();
		st.closeSerialPort();
		System.out.println(":::>"+redata);
		Map<String,String> reMap=new HashMap<String,String>();
		reMap.put("data", redata);
		return reMap;
	}
	static String[] mmp={"257","264","251","196","207","226","270"};
	@ResponseBody
	@RequestMapping("cao")
	public List<List<String>> ceshi(){
		List<String> xVal=new ArrayList<String>();
		List<String> sereis=new ArrayList<String>();
		List<List<String>> result=new ArrayList<List<String>>();
		for(int i=0;i<5;i++){
			/*SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
			xVal.add(sdf.format(new Date(System.currentTimeMillis())));*/
			sereis.add(mmp[i]);
			xVal.add("数据项"+i);
			//sereis.add(Integer.toString((int)(Math.random()*300)+200));
		}
		String temp=mmp[0];
		System.arraycopy(mmp, 1, mmp, 0, mmp.length-1);
		mmp[mmp.length-1]=temp;
		result.add(xVal);
		result.add(sereis);
		return result;
	}
	@ResponseBody
	@RequestMapping("ni")
	public List<String> ceshi2(){
		List<String> result=new ArrayList<String>();
		result.add(Integer.toString((int)(Math.random()*100)+200));
		result.add("数据项"+(int)(Math.random()*10));
		return result;
	}
}
