package com.hr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hr.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author Hanniel_Yang
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 登录验证
     * @param employeeNumber
     * @param password
     * @return
     */
    Employee checkLogin(@Param("employeeNumber") Integer employeeNumber,
                        @Param("password") String password);

    /**
     * 根据employeeNumber查询信息
     * @param employeeNumber
     * @return
     */
    Employee selectByNumber(Integer employeeNumber);

    /**
     * 根基 部门id 查询员工
     * @param DepartmentNumber
     * @return
     */
    Employee selectByDepartmentNumber(Integer DepartmentNumber);

    /**
     * 根据部门删除员工
     * @param departmentNumber
     */
    void deleteByDepartmentNumber(Integer departmentNumber);
}