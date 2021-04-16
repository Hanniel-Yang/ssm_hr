package com.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Department;
import com.hr.bean.History;
import com.hr.bean.Position;
import com.hr.dao.DepartmentMapper;
import com.hr.dao.HistoryMapper;
import com.hr.dao.PositionMapper;
import com.hr.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工档案 实现
 * @author Hanniel_Yang
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>  implements HistoryService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;
    /**
     * history对象setDepartment setPosition
     * @param history
     * @return
     */
    public History setObject(History history){
        Integer departmentNumber = history.getDepartmentNumber();
        if (departmentNumber != null) {
            Department department = departmentMapper.selectByNumber(departmentNumber);
            history.setDepartment(department);
        }else{
            history.setDepartment(null);
        }
        Integer positionNumber = history.getPositionNumber();
        if (positionNumber != null) {
            Position position = positionMapper.selectByNumber(positionNumber);
            history.setPosition(position);
        }else{
            history.setPosition(null);
        }
        return history;
    }


    /**
     * 分页查询离休员工
     *
     * @param pageNo
     * @return
     */
    @Override
    public Page<History> selectRetireByPage(int pageNo) {
        Page<History> page = new Page<History>(pageNo, 5, "id");
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        List<History> hList = baseMapper.selectRetireByPage(page);
        for(History h : hList){
            setObject(h);
        }
        page.setRecords(hList);
        return page;
    }

    /**
     * 查询一个员工档案信息
     *
     * @param id
     * @return
     */
    @Override
    public History selectHistory(Integer id) {
        History history = baseMapper.selectById(id);
        setObject(history);
        return history;
    }

    /**
     * 分页查询所有员工档案
     *
     * @param pageNo
     * @return
     */
    @Override
    public Page<History> selectLisByPage(int pageNo) {
        Page<History> page = new Page<History>(pageNo, 5);
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        List<History> hList = baseMapper.selectPage(page, null);
        for(History h : hList){
            setObject(h);
        }
        page.setRecords(hList);
        return page;
    }

    /**
     * 根据员工的工号查询信息
     *
     * @param employeeNumber
     * @return
     */
    @Override
    public History selectByNumber(Integer employeeNumber) {
        return baseMapper.selectByNumber(employeeNumber);
    }

    /**
     * 查询所有员工档案信息
     *
     * @return
     */
    @Override
    public List<History> selectList() {
        List<History> hList = baseMapper.selectList(new EntityWrapper<History>());
        for(History h : hList){
            setObject(h);
        }
        return hList;
    }
}
