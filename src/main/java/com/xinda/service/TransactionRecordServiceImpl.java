package com.xinda.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.TransactionRecordMapper;
import com.xinda.entity.TransactionRecord;
@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

	@Autowired
	private TransactionRecordMapper trDao;
	@Override
	public List<TransactionRecord> findAllRecord() {
		List<TransactionRecord> result=trDao.selectAll();
		return result;
	}
@Override
public Map<String, List> findMeterRechargeRecord(Integer meterId,
		int groupType, String start, String over) {
	Map<String, List> result=new HashMap<String, List>();
	List<String> groupBy=trDao.selectForMeterGroup(meterId, groupType,start,over);
	List<BigDecimal> money=trDao.selectForMeterVal(meterId, groupType,start,over);
	
	result.put("Yval", groupBy);
	result.put("Xval", money);
	return result;
}
@Override
public Map<String, List> findBranchRechargeRecord(String branchNum,
		int groupType, String start, String over) {
	Map<String,List> result=new HashMap<String, List>();
	List<String> group=trDao.selectMonthGroupByMonth(branchNum, groupType, start, over);
	List<BigDecimal> money=trDao.selectMoneyGroupByMonth(branchNum, groupType, start, over);
	result.put("Yval", group);
	result.put("Xval", money);
	return result;
}
}
