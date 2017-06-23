package com.xinda.dao;

import com.xinda.entity.CurrentPrice;

public interface CurrentPriceMapper {
	
	int updateCurrentPrice(CurrentPrice record);
	
	CurrentPrice selectCurrentPrice();
	
    int updateByPrimaryKey(CurrentPrice record);
}