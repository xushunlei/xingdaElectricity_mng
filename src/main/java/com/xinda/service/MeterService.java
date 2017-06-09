package com.xinda.service;

import java.util.List;

import com.xinda.entity.Meter;

public interface MeterService
{
	/**
	 * 电表充值
	 * @param meterNum 电表编号
	 * @param price 充值金额
	 * @return 成功与否
	 */
	public boolean recharge(String meterNum, double price);
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
	 * 根据相似条件查找电表列表
	 * @param meter 查找模板。主要设置网点、类型、状态
	 * @return List<Meter> 电表列表
	 */
	public List<Meter> findMetersLikeMeter(Meter meter);
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
	public List<Meter> findAllMeters();
}