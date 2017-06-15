package com.xinda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.Meter;

public interface MeterMapper {
    /**查找所有电表*/
	List<Meter> selectAllMeters(@Param("start")Integer startNo, @Param("size")Integer pageSize);
	/**查找所有电表数量*/
	Integer selectAllMetersCount();
	/**根据电表状态或类型条件查找电表并分页*/
	List<Meter> selectMetersLikeExample1(Meter meter, @Param("start")Integer currentPage, @Param("size")Integer pageSize);
	
	/**根据所属用户或网点条件查找电表并分页*/
	List<Meter> selectMetersLikeExample2(@Param("condi")String condition, @Param("start")Integer currentPage, @Param("size")Integer pageSize);
	/**
	 * 根据条件搜索电表
	 * @param branchNum 网点编号
	 * @param meterType 电表类型
	 * @param meterStatus 电表状态
	 * @param condition 搜索条件
	 * @param start 分页显示开始条目
	 * @param pageSize 显示条目数量
	 * @return
	 */
	List<Meter> selectMetersForCondition(
			@Param("branchNum")String branchNum,
			@Param("meterType")Byte meterType, 
			@Param("meterStatus")Byte meterStatus, 
			@Param("condition")String condition, 
			@Param("start")Integer start, 
			@Param("size")Integer pageSize);
	
	Integer selectCountForCondition(
			@Param("branchNum")String branchNum,
			@Param("meterType")Byte meterType, 
			@Param("meterStatus")Byte meterStatus, 
			@Param("condition")String condition);
}