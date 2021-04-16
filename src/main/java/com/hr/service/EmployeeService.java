package com.hr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hr.bean.Employee;

import java.util.List;

/**
 * 部门 service
 * @author Hanniel_Yang
 */
public interface EmployeeService  extends IService<Employee> {

    /**
     *登录验证
     * @param employeeNumber
     * @param password
     * @return
     */
    Employee checkLogin(Integer employeeNumber, String password);

    /**
     *分页查询 用户（倒叙）
     * @param pageNo
     * @return
     */
    Page<Employee> selectListByPage(int pageNo);

    /**
     * 添加员工  同时插入至员工表和员工档案表
     * @param employee
     */
    void addEmployee(Employee employee);

    /**
     * 查询一个员工
     * @param id
     * @return
     */
    Employee selectEmployee(Integer id);

    /**
     * 更新员工信息
     * @param employee
     * @param status
     * @param manager
     */
    void updateEmployee(Employee employee, String status, String manager);

    /**
     * 删除员工信息
     * @param id
     */
    void  deleteEmployee(Integer id);

    /**
     * 根据 employeeNumber 查询用户
     * @param employeeNumber
     * @return
     */
    Employee selectByNumber(Integer employeeNumber);

    /**
     * 根据用户输入 查询用户  模糊查询
     * @param input
     * @param pageNo
     * @return
     */
    Page<Employee> search(String input, int pageNo);

    /**
     * 测试SQL拼接
     * @param employeeNumber
     * @param password
     * @return
     */
    List<Employee> select(Integer employeeNumber, String password);

    /**
     * 根据部门id 删除员工
     * @param departmentNumber
     */
    void deleteByDepartmentNumber(Integer departmentNumber);

}
