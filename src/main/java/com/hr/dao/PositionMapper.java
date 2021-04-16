package com.hr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hr.bean.Position;

/**
 * @author Hanniel_Yang
 */
public interface PositionMapper extends BaseMapper<Position> {
    /**
     * 根据PositionNumber查询信息
     * @param positionNumber
     * @return
     */
    Position selectByNumber(Integer positionNumber);
}