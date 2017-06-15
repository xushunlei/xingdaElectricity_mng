package com.xinda.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.xinda.entity.Branch;
import com.xinda.entity.Meter;
import com.xinda.entity.User;
import com.xinda.service.BranchService;
import com.xinda.service.MeterService;
import com.xinda.service.UserService;

@Controller
@RequestMapping("admin/")
public class AdminController
{
	@Autowired
	private UserService userService;
	@Autowired
	private MeterService meterservice;
	@Autowired
	private BranchService branchService;
	@RequestMapping("manageView")
	public String managePage(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null&&loginUser.getUserRole()==1){
			session.setAttribute("branchList", branchService.findAllBranch());
			//session.setAttribute("userList", userService.findUsers());//修改。不展示用户列表，改以展示电表列表
			//session.setAttribute("meterList", meterservice.findAllMeters());
			session.setAttribute("all_meter_count", meterservice.findAllMetersCount());
			//return "userlist";
			return "managePage";
		}else{
			return "index";
		}
	}
	@RequestMapping("detailView")
	public String detailView(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null&&loginUser.getUserRole()==1){
			session.setAttribute("branchList", branchService.findAllBranch());
			//session.setAttribute("userList", userService.findUsers());//修改。不展示用户列表，改以展示电表列表
			//session.setAttribute("meterList", meterservice.findAllMeters());
			session.setAttribute("all_meter_count", meterservice.findAllMetersCount());
			//return "userlist";
			return "detailPage";
		}else{
			return "index";
		}
	}
	@ResponseBody
	@RequestMapping(value="meter_page")
	public List<Meter> meterPage(HttpServletRequest request){
		int currentPage;//页码
		String cPageString=request.getParameter("pageNo");
		if(cPageString!=null&&cPageString.trim()!=""){
			currentPage=Integer.parseInt(cPageString);
		}else {
			currentPage=1;
		}
		int pageSize;//容量
		String pageNoString=request.getParameter("pageSize");
		if(pageNoString!=null&&pageNoString.trim()!=""){
			pageSize=Integer.parseInt(pageNoString);
		}else{
			pageSize=10;
		}
		List<Meter> res=new ArrayList<Meter>();
		String meterType=request.getParameter("meterType");
		String meterStatus=request.getParameter("meterStatus");
		String branchNum=request.getParameter("branchNum");//网点编号
		String condition=request.getParameter("seachfor");//搜索条件：用户姓名、身份证、电话
		res=meterservice.findMeterListForCondition(currentPage, pageSize, branchNum, meterType, meterStatus, condition);
		/*if(branchNum!=null&&branchNum.trim()!=""){
			
		}
		if(condition!=null&&condition.trim()!=""){
			res= meterservice.findMetersLikeMeter(condition, currentPage, pageSize);
		}else{
			res= meterservice.findAllMeters(currentPage, pageSize);
		}*/
		System.out.println(res);
		return res;
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
			e.printStackTrace();
		} catch (IOException e)
		{
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
	/**查询网点所有电表*/
	@RequestMapping("getmeters")
	public ModelAndView getMeters(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		Meter meter=new Meter();
		Branch branch=new Branch();
		String branchNum=request.getParameter("branch_num");
		int currentPage;
		String cPageString=request.getParameter("pageNo");
		if(cPageString!=null&&cPageString.trim()!=""){
			currentPage=Integer.parseInt(cPageString);
		}else {
			currentPage=1;
		}
		int pageSize;
		String pageNoString=request.getParameter("pageSize");
		if(pageNoString!=null&&pageNoString.trim()!=""){
			pageSize=Integer.parseInt(pageNoString);
		}else{
			pageSize=10;
		}
		if(branchNum!=null){
			branch.setBranchNumber(branchNum);
			meter.setMeterBranch(branch);
			mav.addObject(meterservice.findMetersLikeMeter(branchNum,currentPage,pageSize));
		}
		return mav;
	}
	/**电表充值*/
	@RequestMapping("payment")
	public String payment(HttpServletRequest request, HttpServletResponse response){
		String meterNum=request.getParameter("meter_num");
		Double price=Double.parseDouble(request.getParameter("price"));
		if(meterservice.recharge(meterNum, price)){
			
		}
		return "";
	}
}
