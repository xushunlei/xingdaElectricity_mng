package com.xinda.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xinda.entity.TransactionRecord;
//充值记录
public interface TransactionRecordMapper {
    int insert(TransactionRecord record);

    int insertSelective(TransactionRecord record);

    List<TransactionRecord> selectAll();
    
    List<Map> selectByBranchGroupMonth(String branchNumber);
    
    List<BigDecimal> selectMoneyGroupByMonth(String branchNumber);
    
    List<String> selectMonthGroupByMonth(String branchNumber);
}