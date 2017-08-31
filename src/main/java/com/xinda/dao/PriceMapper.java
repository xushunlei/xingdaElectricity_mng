package com.xinda.dao;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.Price;

public interface PriceMapper {
	/**删除记录：用于手动立即修改价格时，表中未启用的价格记录*/
    int deleteByActive();
    /**插入一条历史记录*/
    int insertSelective(Price record);
    /**根据状态筛选记录。0：未启用，1：当前，2：历史*/
    Price selectByActive(Integer active);
    /**根据ID修改记录。修改状态和到期时间*/
    int updatePrice(Price record);
    /**获取记录的日期列表，用作图表的横坐标*/
    List<String> selectChangeday();
    /**获取记录的历史值，用作图表的纵坐标*/
    List<BigDecimal> selectChangeval();
}