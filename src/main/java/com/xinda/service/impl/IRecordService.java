package com.xinda.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.RechargeRecordMapper;
import com.xinda.entity.RechargeRecord;
import com.xinda.service.RecordService;
@Service
public class IRecordService implements RecordService {
	@Autowired
	private RechargeRecordMapper rechargeDao;
	@Override
	public List<RechargeRecord> findAllRecord() {
		return rechargeDao.selectAll();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> findBranchRechargeRecord(Integer zoneId,
			int groupType, String startDate, String endDate) {
		Map<String,List> result=new HashMap<String, List>();
		List<String> group=rechargeDao.selectMonthGroupBy(zoneId, groupType, startDate, endDate);
		List<BigDecimal> money=rechargeDao.selectMoneyGroupBy(zoneId, groupType, startDate, endDate);
		result.put("Yval", group);
		result.put("Xval", money);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> findMeterRechargeRecord(int meterId,
			int groupType, String startDate, String endDate) {
		Map<String, List> result=new HashMap<String, List>();
		List<String> groupBy=rechargeDao.selectForMeterGroup(meterId, groupType,startDate,endDate);
		List<BigDecimal> money=rechargeDao.selectForMeterVal(meterId, groupType,startDate,endDate);
		
		result.put("Yval", groupBy);
		result.put("Xval", money);
		return result;
	}

}
