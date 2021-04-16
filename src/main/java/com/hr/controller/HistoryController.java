package com.hr.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Employee;
import com.hr.bean.History;
import com.hr.service.EmployeeService;
import com.hr.service.HistoryService;
import com.hr.util.MTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 员工档案
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    EmployeeService employeeService;
    /**
     * 离休员工管理（查询离休员工）
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping("retireListPage")
    public String selectRetireByPage(Model model, int pageNo){
        Page<History> page = historyService.selectRetireByPage(pageNo);
        model.addAttribute("page", page);
        return "admin/retire_list";
    }

    /**
     * 员工档案管理（档案列表）
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Model model){
        List<History> hList = historyService.selectList();
        model.addAttribute("hList", hList);
        return "admin/history_list";
    }

    /**
     * 查看员工信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("{id}/detail")
    public String selectHistory(@PathVariable Integer id, Model model){
        History history = historyService.selectHistory(id);
        model.addAttribute("history", history);
        return "admin/history_detail";
    }

    /**
     * 修改员工信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("{id}/toUpdate")
    public String toUpdate(Model model, @PathVariable Integer id){
        History history = historyService.selectHistory(id);
        if (history.getStatus().equals("在职")) {
            Employee employee = employeeService.selectByNumber(history.getEmployeeNumber());
            return "redirect:/employee/"+ employee.getId() +"/toUpdate.do";
        }else{
            model.addAttribute("history", history);
            return "admin/history_update";
        }
    }

    /**
     * 修改离休员工信息
     * @param id
     * @param history
     * @param date
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateById(@PathVariable Integer id, History history, String date){
        history.setId(id);
        history.setBirthday(MTimeUtil.stringParse(date));
        historyService.updateById(history);
        return "redirect:/history/retireListPage.do?pageNo=1";
    }

}
