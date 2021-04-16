package com.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.bean.Leave;
import com.hr.dao.DepartmentMapper;
import com.hr.dao.EmployeeMapper;
import com.hr.dao.LeaveMapper;
import com.hr.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hanniel_Yang
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 为leave对象setEmployee setDepartment
     *
     * @param leave
     * @return
     */
    public Leave setObject(Leave leave) {
        Integer employeeNumber = leave.getEmployeeNumber();
        Employee employee = employeeMapper.selectByNumber(employeeNumber);
        leave.setEmployee(employee);

        Integer departmentNumber = leave.getDepartmentNumber();
        Department department = departmentMapper.selectByNumber(departmentNumber);
        leave.setDepartment(department);
        return leave;
    }

    /**
     * 查询所有请假记录
     *
     * @return
     */
    @Override
    public List<Leave> selectList() {
        List<Leave> list = baseMapper.selectList(new EntityWrapper<Leave>().orderBy("start_time", false));
        for (Leave leave : list) {
            //为leave对象setEmployee setDepartment
            setObject(leave);
        }
        return list;
    }

    /**
     * 查询一个请假记录
     *
     * @param id
     * @return
     */
    @Override
    public Leave selectLeave(Integer id) {
        Leave leave = baseMapper.selectById(id);
        //为leave对象setEmployee setDepartment
        setObject(leave);
        return leave;
    }

    /**
     * 更改批准状态
     *
     * @param id
     */
    @Override
    public void updateStatus(Integer id) {
        Leave leave = baseMapper.selectById(id);
        leave.setStatus("已批准");
        baseMapper.updateById(leave);
    }

    /**
     * 查询一个人的请假记录
     *
     * @param employeeNumber
     * @param pageNo
     * @return
     */
    @Override
    public Page<Leave> selectByEmployee(Integer employeeNumber, int pageNo) {
        Page<Leave> page = new Page<Leave>(pageNo, 2, "status");
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        List<Leave> list = baseMapper.selectPage(page, new EntityWrapper<Leave>()
                .eq("employee_number", employeeNumber));
        for (Leave leave : list) {
            //为leave对象setEmployee setDepartment
            setObject(leave);
        }
        page.setRecords(list);
        return page;
    }

    /**
     * 根据状态查询所有请假记录
     *
     * @param departmentNumber
     * @param status
     * @return
     */
    @Override
    public List<Leave> selectListByStatus(Integer departmentNumber, String status) {
        List<Leave> list = baseMapper.selectList(new EntityWrapper<Leave>().eq("department_number", departmentNumber).eq("status", status).orderBy("id", false));
        for (Leave leave : list) {
            //为leave对象setEmployee setDepartment
            setObject(leave);
        }
        return list;
    }
}
