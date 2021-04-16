package com.hr.service;

import com.baomidou.mybatisplus.service.IService;
import com.hr.bean.Move;

import java.util.List;

/**
 * 员工调动 记录
 * @author Hanniel_Yang
 */
public interface MoveService extends IService<Move> {
    /**
     * 查询所有的调动记录
     *
     * @return
     */
    List<Move> selectList();
}
