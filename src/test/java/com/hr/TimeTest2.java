package com.hr;

import com.hr.bean.Attendance;
import com.hr.dao.AttendanceMapper;
import com.hr.service.AttendanceService;
import com.hr.util.MTimeUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeTest2 {
	@Autowired
	AttendanceService attendanceService;
	@Autowired
	AttendanceMapper attendanceMapper;

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		String str = MTimeUtil.timeFormat(date);
		System.out.println(str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = sdf.format(date);
		System.out.println(str2);
	}

	@Test
	public void  nowDate(){

		Date date = MTimeUtil.nowDate();
		System.out.println(date);
	}

	@Test
	public void test1() throws ParseException {
		Date nowDate = MTimeUtil.nowDate();
		/*Attendance a = attendanceMapper.selectByNumber(1004, nowDate, "加班");
		System.out.println(a.toString());*/
		/*//获取当前时间
		Date nowTime = MTimeUtil.nowTime();
		//获取当前日期
		Date nowDate = MTimeUtil.nowDate();
		Attendance attendance = new Attendance();
		attendance.setDay(nowDate);
		attendance.setStartTime(nowTime)*/
		String str = MTimeUtil.timeFormat(nowDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(sdf.parse(str));


	}
}
