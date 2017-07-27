package com.xinda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.DosageRecordMapper;
import com.xinda.entity.DosageRecord;
@Service
public class DosageRecordServiceImpl implements DosageRecordService {
	@Autowired
	private DosageRecordMapper dosageDao;
	@Override
	public List<DosageRecord> findRecordByMeter(String meterNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveRecord(DosageRecord record) {
		return dosageDao.insertSelective(record)==1;
	}

}
