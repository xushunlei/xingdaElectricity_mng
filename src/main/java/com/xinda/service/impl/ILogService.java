package com.xinda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.LogMapper;
import com.xinda.entity.Log;
import com.xinda.service.LogService;
import com.xinda.service.MeterService;
import com.xinda.service.ZoneService;
@Service
public class ILogService implements LogService {
    @Autowired
    private LogMapper logDao;
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private MeterService meterService;
    @Override
    public List<Log> findLogByMeter(Integer meterId) {
	// TODO Auto-generated method stub
	return logDao.selectByMeter(meterId);
    }

    @Override
    public boolean saveLog(Log record) {
	// TODO Auto-generated method stub
	return logDao.insert(record)==1;
    }
    @Override
    public List<Map<String, Object>> getReferVal(Integer userRole,List<Integer>meterIds) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("role", userRole);
	map.put("ids", meterIds);
	
        return logDao.selectValueAndDateByMeterIds(map);
    }
}
