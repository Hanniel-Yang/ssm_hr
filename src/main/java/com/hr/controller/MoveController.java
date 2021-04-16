package com.hr.controller;

import com.hr.bean.Move;
import com.hr.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 员工调动记录
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("move")
public class MoveController {

    @Autowired
    private MoveService moveService;

    @RequestMapping("list")
    public String selectList(Model model){
        List<Move> list = moveService.selectList();
        model.addAttribute("mList",list);
        return "admin/move_list";
    }
}
