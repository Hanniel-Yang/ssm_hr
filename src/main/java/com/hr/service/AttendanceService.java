package com.hr.service;

import com.baomidou.mybatisplus.service.IService;
import com.hr.bean.Attendance;

import java.util.List;

/**
 * 考勤
 * @author Hanniel_Yang
 */
public interface AttendanceService  extends IService<Attendance> {
    /**
     * 插入上班记录
     * @param employeeNumber
     */
    void addStart(Integer employeeNumber);

    /**
     * 更新下班记录
     * @param employeeNumber
     */
    void addEnd(Integer employeeNumber);

    /**
     * 查询所有考勤记录
     * @return
     */
    List<Attendance> selectList();

    /**
     * 查询一个员工的考勤记录
     * @param employeeNumber
     * @return
     */
    List<Attendance> selectByEmployee(Integer employeeNumber);
}
