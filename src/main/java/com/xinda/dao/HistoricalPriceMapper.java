package com.xinda.dao;

import com.xinda.entity.HistoricalPrice;

public interface HistoricalPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HistoricalPrice record);

    int insertSelective(HistoricalPrice record);

    HistoricalPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoricalPrice record);

    int updateByPrimaryKey(HistoricalPrice record);
}