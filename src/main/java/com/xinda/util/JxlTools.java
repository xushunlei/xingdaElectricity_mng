/*package com.xinda.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.xinda.entity.Meter;
import com.xinda.service.MeterService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
*//**读取excel表内容并写进数据库。为确保事务性将此段代码移至service层*//*
public class JxlTools {
	private MeterService meterService;
	public void readExcel(String filePath){
		try{
			// 1、构造excel文件输入流对象
			InputStream is = new FileInputStream(filePath);
			// 2、声明工作簿对象 
			Workbook rwb = Workbook.getWorkbook(is);  
	        // 3、获得工作簿的个数,对应于一个excel中的工作表个数  
	        rwb.getNumberOfSheets();
	        // 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称  
	        Sheet oFirstSheet = rwb.getSheet(0);
	        //获取工作表中的总行数 
	        int rows = oFirstSheet.getRows(); 
	        //获取工作表中的总列数
	        int columns = oFirstSheet.getColumns();  
	        for (int i = 1; i < rows; i++) {  
	        	List<String> result=new ArrayList<String>();
	        	Meter meter=new Meter();
	            for (int j = 0; j < columns; j++) {  
	            	//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
	                Cell oCell= oFirstSheet.getCell(j,i); 
	                String cont=oCell.getContents();
	                if(cont==null||cont.trim()=="")break;
	                System.out.println(cont+"\r\n");  
	                result.add(cont);
	            } 
	            if(result.size()>=9){//有9项必填项
	            	meter.setMeterName(result.get(0));
	            	meter.setMeterType(Integer.parseInt(result.get(1)));
	            	meter.setMeterAddress(result.get(2));
	            	meter.setMeterRate(Integer.parseInt(result.get(3)));
	            	meter.setMeterPort(result.get(4));
	            	meter.setMeterUserName(result.get(5));
	            	meter.setMeterUserMobile(result.get(6));
	            	meter.setMeterUserIdcard(result.get(7));
	            	meter.setMeterUserAddress(result.get(8));
	            	if(result.size()==10){
	            		meter.setMeterDescription(result.get(9));
	            	}
	            	meterService.saveMeter(meter);
	            }
	        } 
		}catch(IOException e){
			
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
}
*/