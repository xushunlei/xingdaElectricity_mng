package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.Meter;
import com.xinda.entity.Zone;

public interface MeterService {
	/**查找电表总数*/
	int findAllMetersCount();
	/**查找所有电表*/
	public List<Meter> findAllMeters();
	/**
	 * 分页查找电表
	 * @param currentPage 页码
	 * @param pageSize 每页数目
	 * @return
	 */
	List<Meter> findAllMeters(int currentPage, int pageSize);
	/**保存电表*/
	public boolean saveMeter(Meter meter);
	/**
	 * 根据条件查找电表列表
	 * @param currentPage 页码
	 * @param pageSize 每页最大条目
	 * @param zoneId 网点编号
	 * @param meterType 电表类型
	 * @param meterStatus 电表状态
	 * @param condition 查询条件
	 * @return list 电表列表
	 */
	List<Meter> findMeterListForCondition(int currentPage, int pageSize,
			Integer zoneId, String meterType, String meterStatus,
			String condition);

	Integer findMeterCountForCondition(Integer zoneId, String meterType,
			String meterStatus, String condition);
	/**获取指定区域的所有电表的ID
	 * @param userRole 若角色为普通管理员（0），则根据第二个参数获取电表id；若角色是超级管理员，获取所有电表id
	 * @param zone 角色为普通管理员时，该角色管理的区域*/
	List<Integer> findMeterIdsByZoneList(Integer userRole, Zone zone);

	/**
	 * 电表充值
	 * @param meterNum 电表编号
	 * @param price 充值金额
	 * @return 充值后总金额
	 */
	BigDecimal tx_recharge(Integer meterId, BigDecimal price);

	/**
	 * 设置最大透支金额
	 * @param meterId 操作电表ID
	 * @param overdraft 最大透支金额
	 */
	BigDecimal tx_markOverdraft(Integer meterId, BigDecimal maxValue);

	/**更改电表状态*/
	Boolean tx_modifyStatus(String meterIdString, Integer meterStatus);

	/***/
	boolean tx_importFromExcel(String excelPath,String zoneId);
}
