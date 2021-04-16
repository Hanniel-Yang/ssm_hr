package com.hr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.service.DepartmentService;
import com.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 部门
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    /**
     *部门管理 （部门表单）
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping("listPage")
    public String selectListByPgae(Model model, int pageNo){
        Page<Department> page = departmentService.selectListByPage(pageNo);
        model.addAttribute("page",page);
        return "admin/department_list";
    }

    /**
     * 添加部门 （调换界面）
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        List<Department> dList = departmentService.selectList(new EntityWrapper<Department>()
                .orderBy("department_number", false));
        model.addAttribute("departmentNumber", dList.get(0).getDepartmentNumber()+1);
        return "admin/department_add";
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @RequestMapping("add")
    public String add(Department department){
        departmentService.insert(department);
        return "redirect:/department/listPage.do?pageNo=1";
    }

    /**
     * 部门修改界面 跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("{id}/toUpdate")
    public String toUpdate(@PathVariable Integer id, Model model){
        Department department = departmentService.selectById(id);
        model.addAttribute("department", department);
        return "admin/department_update";
    }

    /**
     * 部门修改
     * @param id
     * @param department
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateById(@PathVariable Integer id, Department department){
        department.setId(id);
        departmentService.updateById(department);
        return "redirect:/department/listPage.do?pageNo=1";
    }

    @RequestMapping("{id}/delete")
    public String deleteById(@PathVariable Integer id){
        Department department = new Department();
        Department department1 = department.selectById(id);
        //删部门下员工
        employeeService.deleteByDepartmentNumber(department1.getDepartmentNumber());

        //删除部门
        departmentService.deleteById(id);

        return "forward:/department/listPage.do?pageNo=1";
    }
}
