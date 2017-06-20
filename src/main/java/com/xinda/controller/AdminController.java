package com.xinda.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.xinda.util.StringDepot;

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
	/**进入管理页面*/
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
	/**进入详情页面*/
	@RequestMapping("detailView")
	public String detailPage(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
		if(loginUser!=null&&loginUser.getUserRole()==1){
			session.setAttribute("branchList", branchService.findAllBranch());
			//session.setAttribute("userList", userService.findUsers());//修改。不展示用户列表，改以展示电表列表
			session.setAttribute("meterList", meterservice.findAllMeters(1,10));
			session.setAttribute("all_meter_count", meterservice.findAllMetersCount());
			//return "userlist";
			return "detailPage";
		}else{
			return "index";
		}
	}
	/**进入报表页面*/
	@RequestMapping("reportView")
	public String reportPage(HttpServletRequest request){
		
		return "reportPage";
	}
	/**进入历史页面*/
	@RequestMapping("histroyView")
	public String histroyPage(HttpServletRequest request){
		return "histroyPage";
	}
	/**进入消息页面*/
	@RequestMapping("messageView")
	public String messagePage(HttpServletRequest request){
		return "messagePage";
	}
	/**查询电表列表*/
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
	/**根据条件查询电表数量*/
	@ResponseBody
	@RequestMapping(value="findCount",method=RequestMethod.POST)
	public Map<String,Integer> getCountFrom(HttpServletRequest request){
		Map<String,Integer> redata=new HashMap<String, Integer>();
		String meterType=request.getParameter("meterType");
		String meterStatus=request.getParameter("meterStatus");
		String branchNum=request.getParameter("branchNum");
		String condition=request.getParameter("seachWord");
		redata.put("total_count", meterservice.findMeterCountForCondition(branchNum, meterType, meterStatus, condition));
		return redata;
	}
	/**启用用户*/
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
	/**停用用户*/
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
	/**重置密码*/
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
	@ResponseBody
	@RequestMapping(value="payment", method=RequestMethod.POST)
	public Map<String, Object> payment(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result=new HashMap<String, Object>();
		String meterIdString=request.getParameter("meter_id");
		Integer meterId=null;
		if(meterIdString!=null&&meterIdString.trim()!=""){
			meterId=Integer.parseInt(meterIdString);
		}
		String priceString=request.getParameter("price");
		Double price=null;
		if(priceString!=null&&priceString.trim()!=""&&Pattern.matches(StringDepot.DOUBLE_REG_EX, priceString.trim())){
			price=Double.parseDouble(request.getParameter("price"));
		}
		if(meterId==null||price==null){
			result.put("flag", false);
		}else{
			result.put("funds", meterservice.tx_recharge(meterId, price));
			result.put("flag", true);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="markOverdraft", method=RequestMethod.POST)
	public Map<String,Object> markOverdraft(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String meterIdString=request.getParameter("meterId");
		String valueString=request.getParameter("maxValue");
		Long maxValue=null;
		Integer meterId=null;
		if(meterIdString!=null&&meterIdString.trim()!=""){
			meterId=Integer.parseInt(meterIdString);
		}
		if(valueString!=null&&valueString.trim()!=""&&Pattern.matches(StringDepot.INTEGER_REG_EX, valueString.trim())){
			maxValue=new Long(valueString);
		}
		if(meterId==null||maxValue==null){
			result.put("flag", false);
		}else{
			result.put("flag", meterservice.tx_markOverdraft(meterId, maxValue));
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="modifyStatus",method=RequestMethod.POST)
	public Map<String,Boolean> modifyStatus(HttpServletRequest request){
		Map<String, Boolean> result=new HashMap<String, Boolean>();
		String meterIdString=request.getParameter("meterId");
		String meterStatusString=request.getParameter("meterStatus");
		Integer meterId=null;
		Byte meterStatus=null;
		if(meterIdString!=null&&meterIdString.trim()!=""&&Pattern.matches(StringDepot.INTEGER_REG_EX, meterIdString)){
			meterId=Integer.parseInt(meterIdString);
		}
		if(meterStatusString!=null&&meterStatusString.trim()!=""&&Pattern.matches(StringDepot.INTEGER_REG_EX, meterStatusString)){
			meterStatus=Byte.parseByte(meterStatusString);
		}
		if(meterId==null||meterStatus==null){
			result.put("flag", false);
		}else{
			result.put("flag", meterservice.tx_modifyStatus(meterId, meterStatus));
		}
		return result;
	}
}
