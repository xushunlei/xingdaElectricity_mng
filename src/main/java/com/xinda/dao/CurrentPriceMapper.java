package com.xinda.dao;

import com.xinda.entity.CurrentPrice;

public interface CurrentPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrentPrice record);

    int insertSelective(CurrentPrice record);

    CurrentPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentPrice record);

    int updateByPrimaryKey(CurrentPrice record);
}