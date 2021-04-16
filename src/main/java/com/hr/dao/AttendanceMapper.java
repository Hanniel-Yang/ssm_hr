package com.hr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hr.bean.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Hanniel_Yang
 */
public interface AttendanceMapper  extends BaseMapper<Attendance> {
    /**
     * 根据employeeNumber和day查询记录
     * @param employeeNumber
     * @param day
     * @param timeType
     * @return
     */
    Attendance selectByNumber(@Param("employeeNumber") Integer employeeNumber,
                              @Param("day") Date day, @Param("timeType") String timeType);

}