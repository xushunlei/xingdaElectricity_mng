package com.xinda.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinda.entity.User;
import com.xinda.service.LogService;
import com.xinda.service.MeterService;
import com.xinda.service.ZoneService;

@Controller
@RequestMapping("charts/")
public class ChartsController {
    @Autowired
    private LogService logService;
    @Autowired
    private MeterService meterService;
    @Autowired
    private ZoneService zoneService;
    /**
     * 获取昨日各时段所有电表总用电情况
     * @param request
     * @return map中需要包含两个list，一个日期用作横坐标，一个用量。
     * 为了拿到这两个集合需要指定查询的meterId的集合：List<Integer> mIds。
     * 该id集合需要根据操作员的角色判定，若是普通管理员就给出管理区域下的meterId；若是超级管理员就给出所有meterId
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("refer")
    public Map<String,List> getRefer(HttpServletRequest request){
	Map<String,List> result=new HashMap<String,List>();
	User loginUser=(User)request.getSession().getAttribute("loginUser");
	if (loginUser!=null) {
	    List<Integer> mIds=meterService.findMeterIdsByZoneList(loginUser.getUserRole(),loginUser.getUserZone());
	    List<Map<String, Object>> yuanshi=logService.getReferVal(loginUser.getUserRole(),mIds);
	    List<String> keys=new ArrayList<String>();
	    List<BigDecimal> vals=new ArrayList<BigDecimal>();
	    for(Map<String,Object> map:yuanshi){
		keys.add(map.get("x").toString());
		vals.add((BigDecimal)map.get("y"));
	    }
	    result.put("xAxis", keys);
	    result.put("value", vals);
	}
	return result;
    }
}
