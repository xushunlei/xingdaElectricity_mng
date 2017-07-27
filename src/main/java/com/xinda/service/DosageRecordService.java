package com.xinda.service;

import java.util.List;

import com.xinda.entity.DosageRecord;

public interface DosageRecordService {
	/**根据电表查消费记录*/
	public List<DosageRecord> findRecordByMeter(String meterNumber);
	/**插入消费记录*/
	public boolean saveRecord(DosageRecord record);
}
