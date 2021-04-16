package com.hr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Position;
import com.hr.dao.PositionMapper;
import com.hr.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * 职称
 * @author Hanniel_Yang
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    /**
     * 根据PositionNumber查询信息
     *
     * @param positionNumber
     * @return
     */
    @Override
    public Position selectByNumber(Integer positionNumber) {
        return baseMapper.selectByNumber(positionNumber);
    }

    /**
     * 分页查询所有职称（倒序）
     *
     * @param pageNo
     * @return
     */
    @Override
    public Page<Position> selectListByPage(int pageNo) {
        Page<Position> page = new Page<Position>(pageNo, 5, "id");
        page.setAsc(false);
        page.setRecords(baseMapper.selectPage(page, null));
        return page;
    }
}
