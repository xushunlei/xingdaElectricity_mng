package com.xinda.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinda.dao.HistoricalPriceMapper;
import com.xinda.entity.HistoricalPrice;
import com.xinda.entity.User;
@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	private HistoricalPriceMapper priceDao;
	@Override
	public HistoricalPrice findPriceByActive(Byte active) {
		return priceDao.selectByActive(active);
	}
	@Transactional
	@Override
	public boolean editPrice(HistoricalPrice hp) {
		Byte a=hp.getActive();
		boolean b=false;
		int flag=0;
		try {
			if (a.equals(0)) {
				if (priceDao.selectByActive(a) == null) {
					flag = priceDao.insertSelective(hp);
				} else {
					flag = priceDao.updateHistoricalPrice(hp);
				}
			} else if (priceDao.deleteByActive() == 1) {
				flag = priceDao.updateHistoricalPrice(hp);
			}
			if (flag == 1) {
				b = true;
			}else{
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			return b;
		}
	}

	@Override
	public List<HistoricalPrice> inquireHistoricalPrices() {
		// TODO Auto-generated method stub
		return null;
	}

}