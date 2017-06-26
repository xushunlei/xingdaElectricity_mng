package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.HistoricalPrice;
import com.xinda.entity.User;

public interface PriceService
{
	/**
	 * 根据状态查询电价
	 * @param active 0-未来价格，1-当前价格，2-历史价格
	 * @return HistoricalPrice
	 */
	public HistoricalPrice findPriceByActive(Byte active);
	/**
	 * 修改电价
	 * @param HistoricalPrice 
	 */
	public boolean tx_editPrice(HistoricalPrice hp);
	/**
	 * 查询历史电价
	 * @return list 电价列表
	 */
	public List<HistoricalPrice> inquireHistoricalPrices();
}
