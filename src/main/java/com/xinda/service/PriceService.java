package com.xinda.service;

import java.util.List;

import com.xinda.entity.HistoricalPrice;
import com.xinda.entity.User;

public interface PriceService
{
	/**
	 * 修改电价
	 * @param user 操作员
	 * @param currentPrice 当前电价
	 */
	public boolean editPrice(User user, double currentPrice);
	/**
	 * 查询历史电价
	 * @return list 电价列表
	 */
	public List<HistoricalPrice> inquireHistoricalPrices();
}
