package com.hr;

import com.hr.bean.Leave;
import com.hr.service.LeaveService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LeaveTest {
   @Autowired
   LeaveService leaveService;
    @Test
    public void test1(){

        List<Leave> lis = leaveService.selectListByStatus(2001, "未批准");
        System.out.println(lis);
    }

}
