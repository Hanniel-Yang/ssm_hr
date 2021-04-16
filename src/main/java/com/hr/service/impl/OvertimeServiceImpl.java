package com.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.bean.Overtime;
import com.hr.dao.DepartmentMapper;
import com.hr.dao.EmployeeMapper;
import com.hr.dao.OvertimeMapper;
import com.hr.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加班记录
 * @author Hanniel_Yang
 */
@Service
public class OvertimeServiceImpl extends ServiceImpl<OvertimeMapper, Overtime> implements OvertimeService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 为overtime对象setDepartment setPosition
     * @param overtime
     * @return
     */
    public Overtime setObject(Overtime overtime){
        Integer departmentNumber = overtime.getDepartmentNumber();
        Department department = departmentMapper.selectByNumber(departmentNumber);
        overtime.setDepartment(department);

        Integer employeeNumber = overtime.getEmployeeNumber();
        Employee employee = employeeMapper.selectByNumber(employeeNumber);
        overtime.setEmployee(employee);
        return overtime;
    }

    /**
     * 分页查询所有的加班记录
     *
     * @param pageNo
     * @return
     */
    @Override
    public Page<Overtime> selectListByPage(int pageNo) {
        Page<Overtime> page = new Page<Overtime>(pageNo,4,"id");
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        List<Overtime> oList = baseMapper.selectPage(page, null);
        for(Overtime overtime : oList){
            setObject(overtime);
        }
        page.setRecords(oList);
        return page;
    }

    /**
     * 查询一个员工的加班记录
     *
     * @param pageNo
     * @param employeeNumber
     * @return
     */
    @Override
    public Page<Overtime> selectByEmployee(int pageNo, Integer employeeNumber) {
        Page<Overtime> page = new Page<Overtime>(pageNo, 4, "id");
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        //查询一个员工的考勤记录，根据id倒序排序
        List<Overtime> oList = baseMapper.selectPage(page, new EntityWrapper<Overtime>()
                .eq("employee_number", employeeNumber)
                .orderBy("id", false));
        for(Overtime overtime : oList){
            //为attendance对象setEmployee
            setObject(overtime);
        }
        page.setRecords(oList);
        return page;
    }
}
