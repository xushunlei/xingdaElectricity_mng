package com.xinda.service;

import java.util.List;
import java.util.Map;

import com.xinda.entity.Log;

public interface LogService {
	/**根据电表查消费记录*/
	public List<Log> findLogByMeter(Integer meterId);
	/***/
	public List<Map<String,Object>> getReferVal(Integer userRole,List<Integer>meterIds);
	/**插入消费记录*/
	public boolean saveLog(Log record);
}
