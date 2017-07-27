package com.xinda.dao;

import com.xinda.entity.DosageRecord;

public interface DosageRecordMapper {

    int insert(DosageRecord record);

    int insertSelective(DosageRecord record);

    DosageRecord selectByMeter(String meterNumber);
}