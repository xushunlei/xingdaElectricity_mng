package com.xinda.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.Meter;

public interface MeterMapper {
	/**查找所有电表*/
	List<Meter> selectAllMeters(@Param("start")Integer startNo, @Param("size")Integer pageSize);
	/**查找所有电表数量*/
	Integer selectAllMetersCount();
	/**
	 * 根据条件搜索电表
	 * @param zoneId 网点编号
	 * @param meterType 电表类型
	 * @param meterStatus 电表状态
	 * @param condition 搜索条件
	 * @param start 分页显示开始条目
	 * @param pageSize 显示条目数量
	 * @return
	 */
	List<Meter> selectMetersForCondition(
			@Param("zoneId")Integer zoneId,
			@Param("meterType")Integer meterType, 
			@Param("meterStatus")Integer meterStatus, 
			@Param("condition")String condition, 
			@Param("start")Integer start, 
			@Param("size")Integer pageSize);
	/**搜索满足条件的电表数*/
	Integer selectCountForCondition(
			@Param("zoneId")Integer zoneId,
			@Param("meterType")Integer meterType, 
			@Param("meterStatus")Integer meterStatus, 
			@Param("condition")String condition);
	
	Meter selectMeterById(Integer meterId);
	/**获取指定区域的所有电表的ID*/
	List<Integer> getMeterIdsByZoneIds(@Param("role")Integer userRole, @Param("zoneId")Integer zoneId);
	/**
	 * 给指定ID电表充值
	 * @param balance 充值金额
	 * @param meterId 电表ID
	 * @return 修改记录数
	 */
	Integer updateBalanceById(@Param("meterBalance")BigDecimal balance, @Param("meterId")Integer meterId);
	/**
	 * 根据ID修改电表记录
	 * @param meter ID必须合法，其他可修改属性：
	 * 	meterStatus 电表状态
	 * 	meterBalance 余额
	 * 	meterMaxOverdraft 最大透支金额
	 * 	meterCurrOverdraft 当前透支金额
	 * 	meterValue 总用电量
	 * 	meterTotalPay 总消费金额
	 * 	meterAddress 通讯地址
	 * 	meterRate 波特率
	 *  meterPort 端口号
	 * @return
	 */
	int updateMeterById(Meter meter);
	/**插入一个电表*/
	int insertOne(Meter meter);
}