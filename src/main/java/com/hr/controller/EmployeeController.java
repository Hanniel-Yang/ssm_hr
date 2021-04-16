package com.hr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Department;
import com.hr.bean.Employee;
import com.hr.bean.History;
import com.hr.bean.Position;
import com.hr.service.DepartmentService;
import com.hr.service.EmployeeService;
import com.hr.service.HistoryService;
import com.hr.service.PositionService;
import com.hr.util.MTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private HistoryService historyService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("login")
    public String toLogin(){
        return "login";
    }

    /**
     * 检查登录
     * @param session
     * @param employee
     * @return
     */
    @RequestMapping("checkLogin")
    public String checkLogin(HttpSession session, Employee employee) {
        Employee employee2 = employeeService.checkLogin(employee.getEmployeeNumber(),
                employee.getPassword());
        if (employee2 != null) {
            session.setAttribute("loged", employee2);
            String level = employee2.getPosition().getLevel();
            if ("人事部主任".equals(level)) {
                return "admin/index1";
            } else if ("人事部员工".equals(level)) {
                return "admin/index2";
            } else if ("部门经理".equals(level)) {
                return "admin/index3";
            } else {
                return "admin/index4";
            }
        } else {
            return "login";
        }
    }

    /**
     * 登录成功跳转
     * @return
     */
    @RequestMapping("welcome")
    public String toWelcome(){
        return "welcome";
    }

    /**
     * 查询员工列表
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "listPage")
    public String selectList(Model model, int pageNo){
        Page<Employee> page = employeeService.selectListByPage(pageNo);
        model.addAttribute("page", page);
        return "admin/employee_list";
    }

    /**
     * 查看员工信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("{id}/detail")
    public String selectEmployee(@PathVariable Integer id, Model model){
        Employee employee = employeeService.selectEmployee(id);
        model.addAttribute("employee", employee);
        return "admin/employee_detail";
    }

    /**
     * 添加员工 跳转
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        List<History> eList = historyService.selectList(new EntityWrapper<History>()
                .orderBy("employee_number", false));
        model.addAttribute("employeeNumber",eList.get(0).getEmployeeNumber()+1);
        List<Department> dList = departmentService.selectList(new EntityWrapper<Department>());
        model.addAttribute("dList", dList);
        List<Position> pList = positionService.selectList(new EntityWrapper<Position>());
        model.addAttribute("pList", pList);
        return "admin/employee_add";
    }

    /**
     * 添加员工表单
     * @param employee
     * @param date
     * @return
     */
    @RequestMapping("add")
    public String add(Employee employee, String date) {
        employee.setBirthday(MTimeUtil.stringParse(date));
        employeeService.addEmployee(employee);
      /*  return "redirect:/employee/listPage?pageNo=1";*/
        return "redirect:/employee/listPage.do?pageNo=1";
    }


    /**
     * 修改员工信息 跳转
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("{id}/toUpdate")
    public String toUpdate(Model model, @PathVariable Integer id){
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        List<Department> dList = departmentService.selectList(new EntityWrapper<Department>());
        model.addAttribute("dList", dList);
        List<Position> pList = positionService.selectList(new EntityWrapper<Position>());
        model.addAttribute("pList", pList);
        return "admin/employee_update";
    }

    /**
     * 更新员工表单
     * @param id
     * @param employee
     * @param date
     * @param status
     * @param session
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateById(@PathVariable Integer id, Employee employee, String date, String status,
                             HttpSession session){
        employee.setId(id);
        employee.setBirthday(MTimeUtil.stringParse(date));
        //得到操作人员的名字
        /*Employee employee2 = (Employee) session.getAttribute("loeged");*/
        employeeService.updateEmployee(employee, status, employee.getName());
        /*return "redirect:/employee/listPage?pageNo=1";*/
        return "redirect:/employee/listPage.do?pageNo=1";
    }

    /**
     * 修改个人信息
     * @param id
     * @param employee
     * @param date
     * @param session
     * @return
     */
    @RequestMapping("{id}/updateOneself")
    public String updateOneselfById(@PathVariable Integer id, Employee employee, String date,
                             HttpSession session){
        employee.setId(id);
        employee.setBirthday(MTimeUtil.stringParse(date));
        //得到操作人员的名字
        /*Employee employee2 = (Employee) session.getAttribute("loeged");*/
       employeeService.updateById(employee);

        return "redirect:/employee/oneself/"+ employee.getId() +"/detail.do";
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @RequestMapping("{id}/delete")
    public String deleteById(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return "redirect:/employee/listPage.do?pageNo=1";
    }

    /**
     * 根据id查看个信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("oneself/{id}/detail")
    public String selectEmployee2(@PathVariable Integer id, Model model){
        Employee employee = employeeService.selectEmployee(id);
        model.addAttribute("employee", employee);
        return "admin/oneself_detail";
    }

    /**
     * 修改个人信息 跳转界面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("oneself/{id}/toUpdate")
    public String toUpdate2(Model model, @PathVariable Integer id){
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        return "admin/oneself_update";
    }

    /**
     * 查询
     * @param model
     * @param input
     * @param pageNo
     * @return
     */
    @RequestMapping("search")
    public String search(Model model, String input, int pageNo){
        Page<Employee> page = employeeService.search(input, pageNo);
        model.addAttribute("page", page);
        return "admin/search_result";
    }

    /**
     * 登出--  退回主界面
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loged");
        return "login";
    }
}
