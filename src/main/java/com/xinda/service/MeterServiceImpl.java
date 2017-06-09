package com.xinda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.MeterMapper;
import com.xinda.entity.Meter;
@Service
public class MeterServiceImpl implements MeterService
{
	@Autowired
	private MeterMapper meterDao;
	@Override
	public boolean recharge(String meterNum, double price)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean openMeter(String meterNum)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeMeter(String meterNum)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Meter> findMetersLikeMeter(Meter meter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveMeter(Meter meter)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Meter findByNumber(String meterNum)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meter> findAllMeters()
	{
		List<Meter> rList=meterDao.selectAllMeters();
		System.out.println("list===>"+rList);
		return rList;
	}

}
