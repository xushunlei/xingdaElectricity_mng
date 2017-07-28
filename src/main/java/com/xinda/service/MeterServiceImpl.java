package com.xinda.service;

import gnu.io.SerialPort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinda.dao.MeterMapper;
import com.xinda.entity.Meter;
import com.xinda.util.SerialTool;
@SuppressWarnings("finally")
@Service
public class MeterServiceImpl implements MeterService
{
	@Autowired
	private MeterMapper meterDao;
	@Override
	@Transactional
	public BigDecimal tx_recharge(Integer meterId, double price)
	{
		Meter meter=meterDao.selectMeterById(meterId);
		BigDecimal m=new BigDecimal(price);
		BigDecimal r=m.add(meter.getMeterBalance());
		int res=meterDao.updateBalanceById(r, meterId);
		if(res!=1){
			throw new RuntimeException("The tx_recharge() is abnormal, and method has been rolled back.");
		}
		return r;
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
			example.setMeterStatus(condi);
			example.setMeterType(condi);
			meterList=meterDao.selectMetersLikeExample1(example, (currentPage-1)*pageSize, pageSize);
		}catch(NumberFormatException e){
			/*Branch exBranch=new Branch();
			exBranch.setBranchNumber(condition);
			User exUser=new User();
			exUser.setUserAccount(condition);
			example.setMeterBranch(exBranch);
			example.setMeterUser(exUser);*/
			meterList=meterDao.selectMetersLikeExample2(condition, (currentPage-1)*pageSize, pageSize);
		}finally{
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
	public List<Meter> findAllMeters(){
		return meterDao.selectAllMeters(null,null);
	}
	@Override
	public List<Meter> findAllMeters(Integer currentPage, Integer pageSize)
	{
		List<Meter> rList=meterDao.selectAllMeters((currentPage-1)*pageSize,pageSize);
		return rList;
	}
	@Override
	public Integer findAllMetersCount()
	{
		return meterDao.selectAllMetersCount();
	}
	@Override
	public List<Meter> findMeterListForCondition(Integer currentPage,
			Integer pageSize, String branchNum, String meterType,
			String meterStatus, String condition) {
		if(branchNum!=null&&branchNum.trim()==""){
			branchNum=null;
		}
		if(condition!=null&&condition.trim()==""){
			condition=null;
		}
		Byte mType=null;
		Byte mStatus=null;
		try {
			mType = Byte.valueOf(meterType);
		} catch (NumberFormatException e) {
			System.out.println("搜索电表时，电表类型错误！");
		}try {
			mStatus = Byte.valueOf(meterStatus);
		} catch (NumberFormatException e) {
			System.out.println("搜索电表时，电表状态错误！");
		}
		finally{
			List<Meter> result=meterDao.selectMetersForCondition(branchNum, mType, mStatus, condition, (currentPage-1)*pageSize, pageSize);
			return result;
		}
	}
	@Override
	public Integer findMeterCountForCondition(String branchNum,
			String meterType, String meterStatus, String condition) {
		Byte mType=null;
		Byte mStatus=null;
		if(branchNum!=null&&branchNum.trim()==""){
			branchNum=null;
		}if(condition!=null&&condition.trim()==""){
			condition=null;
		}
		try {
			mType = Byte.valueOf(meterType);
		} catch (NumberFormatException e) {
			System.out.println("查找数量时，电表类型错误！"+mType);
		}try {
			mStatus = Byte.valueOf(meterStatus);
		} catch (NumberFormatException e) {
			System.out.println("查找数量时，电表状态错误！"+mStatus);
		}finally{
			int i=meterDao.selectCountForCondition(branchNum, mType, mStatus, condition);
			return i;
		}
	}
	@Override
	@Transactional
	public boolean tx_markOverdraft(Integer meterId, Long overdraft) {
		Meter meter = new Meter();
		meter.setMeterMaxOverdraft(overdraft);
		meter.setMeterId(meterId);
		int t=meterDao.updateMeterById(meter);
		if(t!=1){
			throw new RuntimeException("rollback tx_markOverdraft()");
//			return false;
		}else{
			
			return true;
		}
	}
	@Override
	@Transactional
	public boolean tx_modifyStatus(String meterIds, Byte meterStatus) {
		SerialTool st=new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		
		for(String meterId:meterIds.split(",")){
			Meter meter=meterDao.selectMeterById(Integer.parseInt(meterId));
			st.setSeriaPortParam(meter.getMeterRate(), SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
			meter.setMeterStatus(meterStatus);//更改状态
			String addrStr=meter.getMeterContactAddress();//获取通讯地址
			if(addrStr.length()%2==1){
				addrStr="0"+addrStr;
			}
			int len=addrStr.length();
			byte[] addr=new byte[6];
			for(int i=0;i<len/2;i++){
				//完成了地址域的倒序和补位
				addr[i]=(byte) Long.parseLong(addrStr.substring(len-i*2-2, len-i*2),16);
			}
			if(meterStatus==0){//供电
				st.sendDataToSeriaPort(SerialTool.getStartupCommand(addr));
			}else if(meterStatus==(byte)2){//拉闸
				st.sendDataToSeriaPort(SerialTool.getShutdownCommand(addr));
			}
			String redata=st.getReceive();
			System.out.println(redata);
			int t=meterDao.updateMeterById(meter);
			if(t!=1){
				throw new RuntimeException("rollback tx_modifyStatus()");
			}
		}
		st.closeSerialPort();
		return true;
	}
}
