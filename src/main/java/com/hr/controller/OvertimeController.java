package com.hr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.bean.Overtime;
import com.hr.service.DepartmentService;
import com.hr.service.EmployeeService;
import com.hr.service.OvertimeService;
import com.hr.util.MTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 加班记录
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("overtime")
public class OvertimeController {

    @Autowired
    OvertimeService overtimeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    /**
     * 员工查看加班信息
     *
     * @param model
     * @param employeeNumber
     * @param pageNo
     * @return
     */
    @RequestMapping("{employeeNumber}/oneself.do")
    public String select(Model model, @PathVariable Integer employeeNumber, int pageNo) {
        Page<Overtime> page = overtimeService.selectByEmployee(pageNo, employeeNumber);
        model.addAttribute("page", page);
        return "admin/oneself_overtime";
    }

    /**
     * 加班管理  （加班记录表单）
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping("listPage")
    public String selectListByPage(Model model, int pageNo){
        Page<Overtime> page = overtimeService.selectListByPage(pageNo);
        model.addAttribute("page",page);
        return "admin/overtime_list";
    }

    /**
     * 安排加班  （跳转界面）
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        //查询出所有的部门
        List<Department> dList = departmentService.selectList(new EntityWrapper<Department>());
        model.addAttribute("dList", dList);
        //查询出所有的员工
        List<Employee> eList = employeeService.selectList(new EntityWrapper<Employee>());
        model.addAttribute("eList", eList );
        return "admin/overtime_add";
    }

    /**
     * 添加 加班记录
     * @param overtime
     * @param date
     * @return
     */
    @RequestMapping("add")
    public String add(Overtime overtime, String date){
        overtime.setDay(MTimeUtil.stringParse(date));
        overtimeService.insert(overtime);
        return "redirect:/overtime/listPage.do?pageNo=1";
    }

    /**
     * 修改 加班信息 （跳转界面）
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("{id}/toUpdate")
    public String toUpdate(Model model, @PathVariable Integer id){
        //查询出要修改的记录信息
        Overtime overtime = overtimeService.selectById(id);
        model.addAttribute("overtime", overtime);
        //查询出所有的部门
        List<Department> dList = departmentService.selectList(new EntityWrapper<Department>());
        model.addAttribute("dList", dList);
        //查询出所有的员工
        List<Employee> eList = employeeService.selectList(new EntityWrapper<Employee>());
        model.addAttribute("eList", eList );
        return "admin/overtime_update";
    }

    /**
     * 修改加班信息
     * @param id
     * @param date
     * @param overtime
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateById(@PathVariable Integer id,  String date, Overtime overtime){
        overtime.setId(id);
        overtime.setDay(MTimeUtil.stringParse(date));
        overtimeService.updateById(overtime);
        return "redirect:/overtime/listPage.do?pageNo=1";
    }

    /**
     * 删除加班记录
     * @param id
     * @return
     */
    @RequestMapping("{id}/delete")
    public String deleteById(@PathVariable Integer id){
        overtimeService.deleteById(id);
        return "redirect:/overtime/listPage.do?pageNo=1";
    }
}
