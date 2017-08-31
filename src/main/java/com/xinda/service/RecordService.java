package com.xinda.service;

import java.util.List;
import java.util.Map;

import com.xinda.entity.RechargeRecord;

public interface RecordService {
	/**搜索所有记录*/
	List<RechargeRecord> findAllRecord();
	/**
	 * 搜索网点的充值记录
	 * @param zoneId 网点编号
	 * @param groupType 分组类型。0-日，1-月，2-年
	 * @param start 开始时间
	 * @param over 结束时间
	 * @return 充值记录的时间列表和金额列表放在一个集合中
	 */
	@SuppressWarnings("rawtypes")
	Map<String, List> findBranchRechargeRecord(Integer zoneId, int groupType,
			String startDate, String endDate);
	/**
	 * 搜索电表的充值记录
	 * @param meterId 电表ID
	 * @param groupType 分组类型。0-日，1-月，2-年
	 * @param start 开始时间
	 * @param over 结束时间
	 * @return 充值记录的时间列表和金额列表放在一个集合中
	 */
	@SuppressWarnings("rawtypes")
	Map<String, List> findMeterRechargeRecord(int meterId, int groupType,
			String startDate, String endDate);

}
