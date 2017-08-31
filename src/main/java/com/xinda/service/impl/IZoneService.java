package com.xinda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.ZoneMapper;
import com.xinda.entity.Zone;
import com.xinda.service.ZoneService;
@Service
public class IZoneService implements ZoneService {
	@Autowired
	private ZoneMapper zoneDao;
	@Override
	public List<Zone> findAllZone() {
		// TODO Auto-generated method stub
		return zoneDao.selectAllZone();
	}
	@Override
	public List<Integer> findAllZoneId() {
	    // TODO Auto-generated method stub
	    return zoneDao.selectAllZoneId();
	}

	@Override
	public boolean saveZone(Zone zone) {
		// TODO Auto-generated method stub
		return zoneDao.insertZone(zone)==1;
	}

}
