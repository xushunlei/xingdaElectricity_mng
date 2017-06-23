package com.xinda.dao;

import com.xinda.entity.HistoricalPrice;

public interface HistoricalPriceMapper {
	/**删除记录：用于手动立即修改价格时，表中未启用的价格记录*/
    int deleteByActive();
    /**插入一条历史记录*/
    int insertSelective(HistoricalPrice record);
    /**根据状态筛选记录。0：未启用，1：当前，2：历史*/
    HistoricalPrice selectByActive(Byte active);
    /**根据ID修改记录。修改状态和到期时间*/
    int updateHistoricalPrice(HistoricalPrice record);
}