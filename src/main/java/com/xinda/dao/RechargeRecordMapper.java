package com.xinda.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.RechargeRecord;

public interface RechargeRecordMapper {
    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    List<RechargeRecord> selectAll();
    /**按阶段获取指定电表充值记录中的金额列表
     * @param meterId 电表ID
     * @param groupType 日期单位，0日、1月、2年
     * @param startDate 开始日期
     * @param endDate 结束日期*/
    List<BigDecimal> selectForMeterVal(@Param("meterId")Integer meterId,@Param("groupType")int groupType,
    		@Param("startDate")String startDate,@Param("endDate")String endDate);
    /**按阶段获取指定电表充值记录中的日期列表
     * @param meterId 电表ID
     * @param groupType 日期单位，0日、1月、2年
     * @param startDate 开始日期
     * @param endDate 结束日期*/
    List<String> selectForMeterGroup(@Param("meterId")Integer meterId,@Param("groupType")int groupType,
    		@Param("startDate")String startDate,@Param("endDate")String endDate);
    /**按阶段获取指定区域充值记录中的金额列表*/
    List<BigDecimal> selectMoneyGroupBy(@Param("zoneId")Integer zoneId,
    		@Param("groupType")int groupType,
    		@Param("startDate")String startDate,
    		@Param("endDate")String endDate);
    /**按阶段获取指定区域充值记录中的日期列表*/
    List<String> selectMonthGroupBy(@Param("zoneId")Integer zoneId,
    		@Param("groupType")int groupType,
    		@Param("startDate")String startDate,
    		@Param("endDate")String endDate);
}