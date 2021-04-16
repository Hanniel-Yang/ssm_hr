package com.hr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hr.bean.Department;

/**
 * @author Hanniel_Yang
 */
public interface DepartmentMapper  extends BaseMapper<Department> {

    /**
     * 根据DepartmentNumber查询信息
     * @param departmentNumber
     * @return
     */
    Department selectByNumber(Integer departmentNumber);
}