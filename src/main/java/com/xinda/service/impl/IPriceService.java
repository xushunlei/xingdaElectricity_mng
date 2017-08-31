package com.xinda.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.PriceMapper;
import com.xinda.entity.Price;
import com.xinda.service.PriceService;
@Service
public class IPriceService implements PriceService {
	@Autowired
	private PriceMapper priceDao;
	@Override
	public boolean tx_editPrice(Price hp) {
		int a=hp.getPriceActive();
		boolean b=false;
		int flag=0;
		if (0==a) {
			if (priceDao.selectByActive(a) == null) {
				flag = priceDao.insertSelective(hp);
			} else {
				flag = priceDao.updatePrice(hp);
			}
		}else{
			Price old=priceDao.selectByActive(1);
			old.setPriceActive(2);
			old.setPriceOverDate(new Timestamp(System.currentTimeMillis()));
			if(priceDao.updatePrice(old)==1){
				flag = priceDao.insertSelective(hp);
				priceDao.deleteByActive();
			}
		}
		if (flag == 1) {
			b = true;
		}else{
			throw new RuntimeException("what happened");
		}
		return b;
	}

	@Override
	public void tx_autoModify() {
		Price p0=priceDao.selectByActive(0);
		if(p0!=null){
			Price p1=priceDao.selectByActive(1);
			p1.setPriceActive(2);
			p1.setPriceOverDate(new Timestamp(System.currentTimeMillis()));
			p0.setPriceActive(1);
			if(priceDao.updatePrice(p1)!=1 || priceDao.updatePrice(p0)!=1){
				throw new RuntimeException("method:tx_autoModify() has execution failed,and rollbacked");
			}
		}
		
	}

	@Override
	public List<String> findGroupBy() {
		return priceDao.selectChangeday();
	}

	@Override
	public List<BigDecimal> findElectricity() {
		return priceDao.selectChangeval();
	}

	@Override
	public Price findPriceByActive(Integer active) {
		return priceDao.selectByActive(active);
	}

}
