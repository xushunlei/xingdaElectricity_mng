/*package com.xinda.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader {
	HSSFWorkbook wb;
	*//**
	 * 读取Excel数据内容
	 * @param is
	 * @return List<Map<Integer, String>>  Map的key是列Id(0代表第一列)，值是具体内容
	 *//*
	public List<Map<Integer, String>> readExcelContentByList(InputStream is) {
		List<Map<Integer, String>> list = new ArrayList<Map<Integer,String>>();
		try {
            //fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(is);
            //wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
		HSSFSheet sheet = wb.getSheetAt(0);
		// 得到总行数
        int rowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer,String> map = new HashMap<Integer, String>();
            
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                
                map.put(j, getCellValue(row.getCell((short) j)).trim().replaceAll("\t\r", ""));
                //str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                j++;
            }
            list.add(map);
        }
		return list;
	}
	//传入cell的值，进行cell值类型的判断，并返回String类型
    private static String getCellValue(HSSFCell cell){
           String value = null;
           //简单的查检列类型
           System.out.println("cell.getCellType():"+cell.getCellType());
           switch(cell.getCellType())  {
                                                                                                                      
               case HSSFCell.CELL_TYPE_STRING://字符串
                   System.out.println("HSSFCell.CELL_TYPE_STRING:"+HSSFCell.CELL_TYPE_STRING);
                   value = cell.getRichStringCellValue().toString();
                   break;
               case HSSFCell.CELL_TYPE_NUMERIC://数字
                   System.out.println("HSSFCell.CELL_TYPE_NUMERIC:"+HSSFCell.CELL_TYPE_NUMERIC);
                   long dd = (long)cell.getNumericCellValue();
                   value = dd+"";
                   break;
               case HSSFCell.CELL_TYPE_BLANK:
                   System.out.println("HSSFCell.CELL_TYPE_BLANK:"+HSSFCell.CELL_TYPE_BLANK);
                   value = "";
                   break;   
               case HSSFCell.CELL_TYPE_FORMULA:
                   System.out.println("HSSFCell.CELL_TYPE_FORMULA:"+HSSFCell.CELL_TYPE_FORMULA);
                   value = String.valueOf(cell.getCellFormula());
                   break;
               case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                   System.out.println("HSSFCell.CELL_TYPE_BOOLEAN:"+HSSFCell.CELL_TYPE_BOOLEAN);
                   value = String.valueOf(cell.getBooleanCellValue());
                   break;
               case HSSFCell.CELL_TYPE_ERROR:
                   System.out.println("HSSFCell.CELL_TYPE_ERROR:"+HSSFCell.CELL_TYPE_ERROR);
                   value = String.valueOf(cell.getErrorCellValue());
                   break;
               default:
                   System.out.println("default");
                   break;
           }
           return value;
       }
}
*/