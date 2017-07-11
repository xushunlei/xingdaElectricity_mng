package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xinda.entity.TransactionRecord;

public interface TransactionRecordService {
	/**搜索所有记录*/
	public List<TransactionRecord> findAllRecord();
	/**
	 * 搜索电表的充值记录
	 * @param meterId 电表ID
	 * @param groupType 分组类型。0-日，1-月，2-年
	 * @param start 开始时间
	 * @param over 结束时间
	 * @return 充值记录的时间列表和金额列表放在一个集合中
	 */
	public Map<String,List> findMeterRechargeRecord(Integer meterId,int groupType,String start,String over);
	/**
	 * 搜索网点的充值记录
	 * @param branchNum 网点编号
	 * @param groupType 分组类型。0-日，1-月，2-年
	 * @param start 开始时间
	 * @param over 结束时间
	 * @return 充值记录的时间列表和金额列表放在一个集合中
	 */
	public Map<String,List> findBranchRechargeRecord(String branchNum,int groupType,String start,String over);
}
