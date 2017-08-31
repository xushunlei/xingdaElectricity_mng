package com.xinda.service.impl;

import gnu.io.SerialPort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinda.dao.MeterMapper;
import com.xinda.dao.ZoneMapper;
import com.xinda.entity.Meter;
import com.xinda.entity.Zone;
import com.xinda.service.MeterService;
import com.xinda.util.SerialTool;
@Service
public class IMeterService implements MeterService {
    @Autowired
    private MeterMapper meterDao;
    @Autowired
    private ZoneMapper zoneDao;
    @Override
    public int findAllMetersCount() {
	// TODO Auto-generated method stub
	return meterDao.selectAllMetersCount();
    }

	
    @Override
    public List<Meter> findAllMeters() {
	// TODO Auto-generated method stub
	return meterDao.selectAllMeters(null,null);
    }
    @Override
    public List<Meter> findAllMeters(int startNo, int pageSize) {
	// TODO Auto-generated method stub
	return meterDao.selectAllMeters(startNo, pageSize);
    }
    @Override
    public boolean saveMeter(Meter meter) {
	// TODO Auto-generated method stub
	return meterDao.updateMeterById(meter)==1;
    }
    @Override
    public List<Integer> findMeterIdsByZoneList(Integer userRole, Zone zone) {
	if(zone!=null)
	    return meterDao.getMeterIdsByZoneIds(userRole,zone.getZoneId());
	else return meterDao.getMeterIdsByZoneIds(userRole,null);
    }
    @Override
    public List<Meter> findMeterListForCondition(int currentPage, int pageSize,
		Integer zoneId, String meterType, String meterStatus, String condition) {
	if(condition!=null&&condition.trim()==""){
	    condition=null;
	}
	Integer mType=null;
	Integer mStatus=null;
	try {
		mType = Integer.valueOf(meterType);
	} catch (NumberFormatException e) {
		System.out.println("搜索电表时，电表类型错误！");
	}try {
		mStatus = Integer.valueOf(meterStatus);
	} catch (NumberFormatException e) {
		System.out.println("搜索电表时，电表状态错误！");
	}
	return meterDao.selectMetersForCondition(zoneId, mType, mStatus, condition, (currentPage-1)*pageSize, pageSize);
    }

    @Override
    public Integer findMeterCountForCondition(Integer zoneId, String meterType,
		String meterStatus, String condition) {
	Integer mType=null;
	Integer mStatus=null;
	if(condition!=null&&condition.trim()==""){
	    condition=null;
	}
	try {
	    mType = Integer.valueOf(meterType);
	} catch (NumberFormatException e) {
	    System.out.println("查找数量时，电表类型错误！"+mType);
	}try {
	    mStatus = Integer.valueOf(meterStatus);
	} catch (NumberFormatException e) {
	    System.out.println("查找数量时，电表状态错误！"+mStatus);
	}
	return meterDao.selectCountForCondition(zoneId, mType, mStatus, condition);
    }

    @Override
    @Transactional
    public BigDecimal tx_recharge(Integer meterId, BigDecimal price) {
	Meter meter=meterDao.selectMeterById(meterId);
	meter.setMeterBalance(meter.getMeterBalance().add(price));
	if(meterDao.updateMeterById(meter)!=1){
	    throw new RuntimeException("The tx_recharge() is abnormal, and method has been rolled back.");
	}
	return meter.getMeterBalance();
    }

    @Override
    @Transactional
    public BigDecimal tx_markOverdraft(Integer meterId, BigDecimal maxValue) {
	Meter meter=meterDao.selectMeterById(meterId);
	meter.setMeterMaxOverdraft(maxValue);
	if(meterDao.updateMeterById(meter)!=1){
	    throw new RuntimeException("The tx_markOverdraft() is abnormal, and method has been rolled back.");
	}
	return meter.getMeterMaxOverdraft();
    }

    @Override
    @Transactional
    public Boolean tx_modifyStatus(String meterIds, Integer meterStatus) {
	SerialTool st=new SerialTool();
	st.scanPorts();
	st.openSerialPort("COM3");
	Meter meter;
	String addrStr;
	for(String meterId:meterIds.split(",")){
	    meter=meterDao.selectMeterById(Integer.parseInt(meterId));
	    st.setSeriaPortParam(meter.getMeterRate(), SerialPort.DATABITS_8,
		    SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
	    meter.setMeterStatus(meterStatus);
	    addrStr=meter.getMeterAddress();
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
	    }else if(meterStatus==2){//拉闸
		st.sendDataToSeriaPort(SerialTool.getShutdownCommand(addr));
	    }
	    String redata=st.getReceive();
	    System.out.println(redata);
	    if(meterDao.updateMeterById(meter)!=1){
		throw new RuntimeException("The tx_modifyStatus() is abnormal, and method has been rolled back.");
	    }
	}
	st.closeSerialPort();
	return true;
    }


    @SuppressWarnings("resource")
    @Override
    @Transactional
    public boolean tx_importFromExcel(String excelPath,String zoneId) {
	//判断是否为excel类型文件
        if(!excelPath.endsWith(".xls")&&!excelPath.endsWith(".xlsx")){
            System.out.println("文件不是excel类型");
        }
        FileInputStream fis =null;
        Workbook wookbook = null;
        try {
            fis = new FileInputStream(excelPath);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
        try {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿
	} catch (IOException e) {
	    //wookbook = new XSSFWorkbook(fis);//得到工作簿
	}
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
      //获得表头
        Row rowHead = sheet.getRow(0);
      //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 9) {
            System.out.println("表头的数量不对!");
        }
      //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
      //要获得属性
        String rate="";
        String phone="";
        Meter m=new Meter();
        if(zoneId!=null&&zoneId.trim()!=""){
            m.setMeterFromZone(zoneDao.selectById(Integer.parseInt(zoneId)));
        }
      //获得所有数据
        for(int i = 1 ; i <= totalRowNum ; i++){
            //获得第i行对象
            Row row = sheet.getRow(i);
            //封装对象
            m.setMeterName(row.getCell(0).toString());
            m.setMeterType(Integer.parseInt(row.getCell(1).toString().substring(0, 1)));
            m.setMeterAddress(row.getCell(2).toString());
            try{
            	rate=row.getCell(3).getStringCellValue();//9600.0
            	m.setMeterRate(Integer.parseInt(rate));
            }catch(Exception ex){
            	//把.**部分即小数部分去掉
            	rate=row.getCell(3).toString().replaceAll(".\\d+$", "");
            	m.setMeterRate(Integer.parseInt(rate));
            	//m.setMeterRate(Integer.parseInt(rate.substring(0,rate.length()-2)));
            }
            
            //m.setMeterRate(Integer.parseInt(rate));
            m.setMeterPort(row.getCell(4).toString());
            m.setMeterUserName(row.getCell(5).toString());
            try{
            	phone=row.getCell(6).getStringCellValue();
            	m.setMeterUserMobile(phone);
            }catch(Exception ex){
            	phone=row.getCell(6).toString();
            	m.setMeterUserMobile(phone.substring(0,phone.length()-2));
            }
            m.setMeterUserIdcard(row.getCell(7).toString());
            m.setMeterUserAddress(row.getCell(8).toString());
            System.out.println(m);
            
            try {
		if (meterDao.insertOne(m) != 1) {
		    return false;
		}
            } catch (Exception e) {
		e.printStackTrace();
            }
        }
        //删除文件
        new File(excelPath).delete();
	return true;
    }

}
