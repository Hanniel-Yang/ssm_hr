package com.hr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hr.bean.Position;

/**
 * 职称
 * @author Hanniel_Yang
 */
public interface PositionService extends IService<Position> {
    /**
     * 根据PositionNumber查询信息
     * @param positionNumber
     * @return
     */
    Position selectByNumber(Integer positionNumber);

    /**
     * 分页查询所有职称（倒序）
     * @param pageNo
     * @return
     */
    Page<Position> selectListByPage(int pageNo);
}
