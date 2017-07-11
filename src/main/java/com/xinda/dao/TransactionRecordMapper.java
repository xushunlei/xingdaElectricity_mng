package com.xinda.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.TransactionRecord;
//充值记录
public interface TransactionRecordMapper {
    int insert(TransactionRecord record);

    int insertSelective(TransactionRecord record);

    List<TransactionRecord> selectAll();
    /**获取电表充值记录中的金额列表
     * @param meterId 电表ID
     * @param groupType 日期单位，0日、1月、2年
     * @param startDate 开始日期
     * @param endDate 结束日期*/
    List<BigDecimal> selectForMeterVal(@Param("meterId")Integer meterId,@Param("groupType")int groupType,
    		@Param("startDate")String startDate,@Param("endDate")String endDate);
    /**获取电表充值记录中的日期列表
     * @param meterId 电表ID
     * @param groupType 日期单位，0日、1月、2年
     * @param startDate 开始日期
     * @param endDate 结束日期*/
    List<String> selectForMeterGroup(@Param("meterId")Integer meterId,@Param("groupType")int groupType,
    		@Param("startDate")String startDate,@Param("endDate")String endDate);
    
    List<BigDecimal> selectMoneyGroupByMonth(@Param("branchNumber")String branchNumber,
    		@Param("groupType")int groupType,
    		@Param("startDate")String startDate,
    		@Param("endDate")String endDate);
    
    List<String> selectMonthGroupByMonth(@Param("branchNumber")String branchNumber,
    		@Param("groupType")int groupType,
    		@Param("startDate")String startDate,
    		@Param("endDate")String endDate);
}