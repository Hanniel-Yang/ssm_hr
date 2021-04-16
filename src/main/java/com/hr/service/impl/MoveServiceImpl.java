package com.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.bean.Move;
import com.hr.bean.Overtime;
import com.hr.dao.DepartmentMapper;
import com.hr.dao.EmployeeMapper;
import com.hr.dao.MoveMapper;
import com.hr.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工调动 记录
 * @author Hanniel_Yang
 */
@Service("moveService")
public class MoveServiceImpl extends ServiceImpl<MoveMapper, Move> implements MoveService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

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
     * 查询所有的调动记录
     *
     * @return
     */
    @Override
    public List<Move> selectList() {
        //查询所有记录，根据id倒序排序
        List<Move> list = baseMapper.selectList(new EntityWrapper<Move>().
                orderBy("id", false));
        for(Move move : list){
            Employee employee = employeeMapper.selectByNumber(move.getEmployeeNumber());
            move.setEmployee(employee);
            Department department = departmentMapper.selectByNumber(move.getBefore());
            move.setDepartment(department);
            Department department2 = departmentMapper.selectByNumber(move.getAfter());
            move.setDepartment2(department2);
        }
        return list;
    }
}
