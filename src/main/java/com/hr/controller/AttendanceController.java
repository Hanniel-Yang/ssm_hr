package com.hr.controller;

import com.hr.bean.Attendance;
import com.hr.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 考勤记录
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    /**
     * 上班签到
     * @param employeeNumber
     * @return
     */
    @RequestMapping("addStart")
    public String addStart(Integer employeeNumber){
        attendanceService.addStart(employeeNumber);

        return "welcome";
    }

    /**
     * 下班签到
     * @param employeeNumber
     * @return
     */
    @RequestMapping("addEnd")
    public String addEnd(Integer employeeNumber){
        attendanceService.addEnd(employeeNumber);
        return "welcome";
    }

    /**
     * 查看考情记录
     * @param model
     * @param employeeNumber
     * @return
     */
    @RequestMapping("{employeeNumber}/oneself")
    public String select(Model model, @PathVariable Integer employeeNumber){
        List<Attendance> list = attendanceService.selectByEmployee(employeeNumber);
        model.addAttribute("aList",list);
        return "admin/oneself_attendance";
    }

    /**
     * 考情管理 考情列表
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String selectList(Model model){
        List<Attendance> list = attendanceService.selectList();
        model.addAttribute("aList",list);
        return "admin/attendance_list";
    }

}
