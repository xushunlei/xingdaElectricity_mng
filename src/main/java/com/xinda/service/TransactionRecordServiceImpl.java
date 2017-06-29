package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

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
	public List<String> findGroupMonth(String branchNumber) {
		// TODO Auto-generated method stub
		return trDao.selectMonthGroupByMonth(branchNumber);
	}
	@Override
	public List<BigDecimal> findMoneyGroupBy(String branchNumber) {
		// TODO Auto-generated method stub
		return trDao.selectMoneyGroupByMonth(branchNumber);
	}

}
