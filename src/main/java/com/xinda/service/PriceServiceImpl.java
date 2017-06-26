package com.xinda.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
	public boolean tx_editPrice(HistoricalPrice hp) {
		Byte a=hp.getActive();
		boolean b=false;
		int flag=0;
//		try {
			if ((byte)0==a) {
				if (priceDao.selectByActive(a) == null) {
					flag = priceDao.insertSelective(hp);
				} else {
					flag = priceDao.updateHistoricalPrice(hp);
				}
			} else {
				HistoricalPrice old=priceDao.selectByActive((byte)1);
				old.setActive((byte)2);
				old.setExpiredDate(new Timestamp(System.currentTimeMillis()));
				if(priceDao.updateHistoricalPrice(old)==1){
					flag = priceDao.insertSelective(hp);
					priceDao.deleteByActive();
				}
			}
			if (flag == 1) {
				b = true;
			}else{
				throw new RuntimeException("what happened");
			}
//		} catch (Exception e) {
//			throw new RuntimeException("what happened");
//		}finally{
			return b;
//		}
	}

	@Override
	public List<HistoricalPrice> inquireHistoricalPrices() {
		// TODO Auto-generated method stub
		return null;
	}

}
