package com.xinda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.MeterMapper;
import com.xinda.entity.Branch;
import com.xinda.entity.Meter;
import com.xinda.entity.User;
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
	public List<Meter> findMetersLikeMeter(String condition, Integer currentPage, Integer pageSize)
	{
		List<Meter> meterList=new ArrayList<Meter>();
		Meter example= new Meter();
		try{
			Byte condi=Byte.valueOf(condition);
			System.out.println("byte condition="+condi);
			example.setMeterStatus(condi);
			example.setMeterType(condi);
			meterList=meterDao.selectMetersLikeExample1(example, (currentPage-1)*pageSize, pageSize);
		}catch(ClassCastException e){
			/*Branch exBranch=new Branch();
			exBranch.setBranchNumber(condition);
			User exUser=new User();
			exUser.setUserAccount(condition);
			example.setMeterBranch(exBranch);
			example.setMeterUser(exUser);*/
			meterList=meterDao.selectMetersLikeExample2(condition, (currentPage-1)*pageSize, pageSize);
		}finally{
			System.out.println(meterList);
			return meterList;
		}
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
	public List<Meter> findAllMeters(Integer currentPage, Integer pageSize)
	{
		List<Meter> rList=meterDao.selectAllMeters((currentPage-1)*pageSize,pageSize);
		//System.out.println("list===>"+rList);
		return rList;
	}

}
