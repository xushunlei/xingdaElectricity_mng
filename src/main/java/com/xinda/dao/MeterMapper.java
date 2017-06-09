package com.xinda.dao;

import java.util.List;

import com.xinda.entity.Meter;

public interface MeterMapper {
    /**查找所有电表*/
	List<Meter> selectAllMeters();
}