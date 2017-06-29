package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.TransactionRecord;

public interface TransactionRecordService {
	/**搜索所有记录*/
	public List<TransactionRecord> findAllRecord();
	
	public List<String> findGroupMonth(String branchNumber);
	public List<BigDecimal> findMoneyGroupBy(String branchNumber); 
}
