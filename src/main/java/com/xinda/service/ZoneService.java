package com.xinda.service;

import java.util.List;

import com.xinda.entity.Zone;

public interface ZoneService {

	List<Zone> findAllZone();
	List<Integer> findAllZoneId();

	boolean saveZone(Zone zone);
	
	
}
