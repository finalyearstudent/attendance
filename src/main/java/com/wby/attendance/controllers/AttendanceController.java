package com.wby.attendance.controllers;

import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.constants.SessionConstants;
import com.wby.attendance.pojos.AttendanceDO;
import com.wby.attendance.serviceimpl.attendance.AttendanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname AttendanceController
 * @Author WangBoyi
 * @Date 2020-2-14 18:22
 * @Description 考勤controller
 * @Version 1.0.0
 **/

@Controller
public class AttendanceController {

	@Autowired
	AttendanceService attendanceService;

	/**
	 * 保存今天的考勤数据
	 * @param attendanceDO
	 * @param request
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@ResponseBody
	@PostMapping("/attendance/makeaattendance")
	public String makeAAttendance(@RequestBody AttendanceDO attendanceDO, HttpServletRequest request){
		Assert.notNull(attendanceDO);
		HttpSession session = request.getSession();
		attendanceDO.setuId((String)session.getAttribute(SessionConstants.LOGIN_USER));
		return JSONObject.toJSONString(attendanceService.makeAAttendance(attendanceDO));
	}

	/**
	 * 获取今天的考勤数据
	 * @param request
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@GetMapping("/attendance/data")
	@ResponseBody
	public String getTodayAttendanceData(HttpServletRequest request){
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute(SessionConstants.LOGIN_USER);
		return attendanceService.getTodayAttendanceData(account);
	}
}
