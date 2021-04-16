package com.hr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Employee;
import com.hr.bean.Leave;
import com.hr.service.LeaveService;
import com.hr.util.MTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 请假
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;
    /**
     * 申请请假跳转
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "admin/leave_add";
    }

    /**
     * 提交申请 （保存申请记录）
     * @param employeeNumber
     * @param leave
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("add")
    public String add(Integer employeeNumber, Leave leave, String start, String end){
        leave.setEmployeeNumber(employeeNumber);
        leave.setStartTime(MTimeUtil.stringParse(start));
        leave.setEndTime(MTimeUtil.stringParse(end));
        leaveService.insert(leave);
        return "redirect:/employee/welcome.do";
    }

    /**
     * 查看请假记录（员工个人）
     * @param session
     * @param pageNo
     * @param model
     * @return
     */
    @RequestMapping("oneself")
    public String selectByEmployee(HttpSession session, int pageNo, Model model){
        Employee employee = (Employee)session.getAttribute("loged");
        Page<Leave> page = leaveService.selectByEmployee(employee.getEmployeeNumber(), pageNo);
        model.addAttribute("page", page);
        return "admin/oneself_leave";
    }

    /**
     * 未批准列表
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("notlist")
    public String selectNotList(Model model, HttpSession session){
        //获取登录用户的信息
        Employee employee = (Employee) session.getAttribute("loged");
        List<Leave> list = leaveService.selectListByStatus(employee.getDepartmentNumber(), "未批准");
        model.addAttribute("list", list);
        return "admin/leave_notlist";
    }

    /**
     * 已批准列表
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("yeslist")
    public String selectYesList(Model model, HttpSession session){
        //获取登录用户的信息
        Employee employee = (Employee) session.getAttribute("loged");
        List<Leave> list = leaveService.selectListByStatus(employee.getDepartmentNumber(), "已批准");
        model.addAttribute("list", list);
        return "admin/leave_yeslist";
    }

    /**
     * 请假记录  （请假表单）
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String selectList(Model model){
        List<Leave> list = leaveService.selectList();
        model.addAttribute("list", list);
        return "admin/leave_list";
    }

    /**
     * 查看未批准记录
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}/detail")
    public String selectLeave(@PathVariable Integer id, Model model){
        Leave leave = leaveService.selectLeave(id);
        model.addAttribute("leave", leave);
        return "admin/leave_detail";
    }

    /**
     * 批准 请假
     * @param id
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateStatus(@PathVariable Integer id){
        leaveService.updateStatus(id);
        return "redirect:/leave/notlist.do";
    }

}
