package com.xinda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.Meter;

public interface MeterMapper {
    /**查找所有电表*/
	List<Meter> selectAllMeters(@Param("start")Integer startNo, @Param("size")Integer pageSize);
	
	/**根据电表状态或类型条件查找电表并分页*/
	List<Meter> selectMetersLikeExample1(Meter meter, @Param("start")Integer currentPage, @Param("size")Integer pageSize);
	
	/**根据所属用户或网点条件查找电表并分页*/
	List<Meter> selectMetersLikeExample2(@Param("condi")String condition, @Param("start")Integer currentPage, @Param("size")Integer pageSize);
}