package com.hr.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hr.bean.Position;
import com.hr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 职称管理
 * @author Hanniel_Yang
 */
@Controller
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 职称管理（职称表单）
     * @param pageNo
     * @param model
     * @return
     */
    @RequestMapping("listPage")
    public String selectListByPage(int pageNo, Model model){
        Page<Position> page = positionService.selectListByPage(pageNo);
        model.addAttribute("page", page);
        return "admin/position_list";
    }

    /**
     * 添加职称  跳转至添加支撑界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        List<Position> dList = positionService.selectList(new EntityWrapper<Position>()
                .orderBy("position_number", false));
        model.addAttribute("positionNumber", dList.get(0).getPositionNumber()+1);
        return "admin/position_add";
    }

    /**
     * 添加职称
     * @param position
     * @return
     */
    @RequestMapping("add")
    public String add(Position position){
        positionService.insert(position);
        return "redirect:/position/listPage.do?pageNo=1";
    }

    /**
     * 修改职称 跳转界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("{id}/toUpdate")
    public String toUpdate(@PathVariable Integer id, Model model){
        Position position = positionService.selectById(id);
        model.addAttribute("position", position);
        return "admin/position_update";
    }

    /**
     * 修改职称
     * @param id
     * @param position
     * @return
     */
    @RequestMapping("{id}/update")
    public String updateById(@PathVariable Integer id, Position position){
        position.setId(id);
        positionService.updateById(position);
        return "redirect:/position/listPage.do?pageNo=1";
    }


    /**
     * 删除 职称
     * @param id
     * @return
     */
    @RequestMapping("{id}/delete")
    public String deleteById(@PathVariable Integer id){
        positionService.deleteById(id);
        return "redirect:/position/listPage.do?pageNo=1";
    }

}
