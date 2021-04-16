package com.hr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hr.bean.Overtime;

/**
 * 加班记录表
 * @author Hanniel_Yang
 */
public interface OvertimeService extends IService<Overtime> {
    /**
     * 分页查询所有的加班记录
     * @param pageNo
     * @return
     */
    Page<Overtime> selectListByPage(int pageNo);

    /**
     *
     * 查询一个员工的加班记录
     * @param pageNo
     * @param employeeNumber
     * @return
     */
    Page<Overtime> selectByEmployee(int pageNo, Integer employeeNumber);
}
