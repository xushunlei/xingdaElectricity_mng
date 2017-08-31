package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import com.xinda.entity.Price;

public interface PriceService {

	boolean tx_editPrice(Price hp);
	public void tx_autoModify();

	List<String> findGroupBy();

	List<BigDecimal> findElectricity();

	/**
	 *根据状态查询电价
	 * @param active 0-未来价格，1-当前价格，2-历史价格
	 */
	Price findPriceByActive(Integer active);
}
