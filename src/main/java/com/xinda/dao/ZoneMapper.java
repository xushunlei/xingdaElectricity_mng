package com.xinda.dao;

import java.util.List;

import com.xinda.entity.Zone;

public interface ZoneMapper {
	/**查找所有区域*/
	List<Zone> selectAllZone();
	List<Integer> selectAllZoneId();
	/**插入一个区域,若成功参数中会自动赋值zoneId*/
	int insertZone(Zone zone);
	/**根据ID查找区域*/
	Zone selectById(Integer zoneId);
	
}