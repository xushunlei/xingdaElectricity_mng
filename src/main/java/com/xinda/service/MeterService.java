package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.Meter;

public interface MeterService
{
	/**
	 * 电表充值
	 * @param meterNum 电表编号
	 * @param price 充值金额
	 * @return 充值后总金额
	 */
	public BigDecimal tx_recharge(Integer meterId, double price);
	/**
	 * 电表开启供电
	 * @param meterNum 电表编号
	 */
	public boolean openMeter(String meterNum);
	/**
	 * 拉闸
	 * @param meterNum 电表编号
	 */
	public boolean closeMeter(String meterNum);
	/**
	 * 根据相似条件查找电表列表,并分页
	 * @param condition 查找条件。主要设置网点、类型、状态、用户名
	 * @param currentPage 查找第几页
	 * @param pageSize 每页显示条目数
	 * @return List<Meter> 电表列表
	 */
	public List<Meter> findMetersLikeMeter(String condition, Integer currentPage, Integer pageSize);
	
	//public Integer findMetersLikeMeterCount();
	/**
	 * 新增电表
	 * @param meter
	 */
	public boolean saveMeter(Meter meter);
	/**
	 * 根据编号查询电表
	 * @param meterNum 电表编号
	 * @return meter 电表对象
	 */
	public Meter findByNumber(String meterNum);
	/**查找所有电表*/
	public List<Meter> findAllMeters(Integer currentPage, Integer pageSize);
	
	public Integer findAllMetersCount();
	/**
	 * 根据条件查找电表列表
	 * @param currentPage 页码
	 * @param pageSize 每页最大条目
	 * @param branchNum 网点编号
	 * @param meterType 电表类型
	 * @param meterStatus 电表状态
	 * @param condition 查询条件
	 * @return list 电表列表
	 */
	public List<Meter> findMeterListForCondition(Integer currentPage, Integer pageSize, String branchNum, String meterType, String meterStatus, String condition);
	
	public Integer findMeterCountForCondition(String branchNum, String meterType, String meterStatus, String condition);
	/**
	 * 设置最大透支金额
	 * @param meterId 操作电表ID
	 * @param overdraft 最大透支金额
	 */
	public boolean tx_markOverdraft(Integer meterId, Long overdraft);
	/**更改电表状态*/
	public boolean tx_modifyStatus(String meterIds, Byte meterStatus);
}
