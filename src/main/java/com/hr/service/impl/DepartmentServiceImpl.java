package com.hr.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hr.bean.Department;
import com.hr.dao.DepartmentMapper;
import com.hr.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门   service 实现
 * @author Hanniel_Yang
 */
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper,Department>  implements DepartmentService {
    @Override
    public Department selectByNumber(Integer departmentNumber) {
        return baseMapper.selectByNumber(departmentNumber);
    }

    @Override
    public Page<Department> selectListByPage(int pageNo) {
        Page<Department> page = new Page<Department>(pageNo, 5, "id");
        //是否为升序 ASC（ 默认： true ）
        page.setAsc(false);
        page.setRecords(baseMapper.selectPage(page, null));
        return page;
    }
}
