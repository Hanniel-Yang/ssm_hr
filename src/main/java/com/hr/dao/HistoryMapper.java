package com.hr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hr.bean.History;

import java.util.List;

/**
 * @author Hanniel_Yang
 */
public interface HistoryMapper  extends BaseMapper<History> {

    /**
     * 分页查询离休休员工（倒序）
     * @param page
     * @return
     */
    List<History> selectRetireByPage(Pagination page);

    /**
     * 根据员工的工号查询信息
     * @param employeeNumber
     * @return
     */
    History selectByNumber(Integer employeeNumber);
}