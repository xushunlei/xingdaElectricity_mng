package com.xinda.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xinda.entity.Log;

public interface LogMapper {
    /**插入抄表记录*/
    int insert(Log record);
    /**根据电表编号查询抄表记录*/
    List<Log> selectByMeter(Integer meterId);
    /**
     * 
     * @param map 
     * role:角色若为1，查询log表所有记录；角色若为0，查询对应管理的电表的所有记录。
     * ids：要查询的电表ID列表
     * @return 返回键值对。键-该记录的日期，值-该日期用电量
     */
    List<Map<String,Object>> selectValueAndDateByMeterIds(Map<String,Object> map);
    /**根据给定时间段查找抄表记录，若结束时间为空默认为当前日期*/
    List<Log> selectBetweenDate(@Param("start")Date start, @Param("end")Date end);
}