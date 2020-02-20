package com.wby.attendance.controllers;

import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.pojos.AttendanceDO;
import com.wby.attendance.pojos.AttendanceDTO;
import com.wby.attendance.pojos.HistoryAttendanceDataVO;
import com.wby.attendance.serviceimpl.attendance.AttendanceService;
import com.wby.attendance.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
	 * 保存考勤数据
	 * @param attendanceDTO
	 * @param request
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@ResponseBody
	@PostMapping("/attendance/makeaattendance")
	public String makeAAttendance(@RequestBody AttendanceDTO attendanceDTO, HttpServletRequest request){
		Assert.notNull(attendanceDTO);
		attendanceDTO.setuId(ProjectUtils.getUidFromSession(request));
		return JSONObject.toJSONString(attendanceService.makeAAttendance(attendanceDTO));
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
		String uid = ProjectUtils.getUidFromSession(request);
		return attendanceService.getTodayAttendanceData(uid);
	}

	@GetMapping("/attendance/history/data")
	@ResponseBody
	public String getAllHistoryAttendanceData(HttpServletRequest request){
		String uid = ProjectUtils.getUidFromSession(request);
		List<AttendanceDO> attendanceDOList = attendanceService.getAttendanceHistory(uid, null, null);
		List<HistoryAttendanceDataVO> historyAttendanceDataVOS = new ArrayList<>();
		for(AttendanceDO attendanceDO : attendanceDOList){
			HistoryAttendanceDataVO historyAttendanceDataVO = new HistoryAttendanceDataVO();
			historyAttendanceDataVO.setActivity(attendanceDO.getActivity());
			historyAttendanceDataVO.setStatus(attendanceDO.getStatus());
			historyAttendanceDataVO.setTask(attendanceDO.getTask());
			historyAttendanceDataVO.setDate(ProjectUtils.getOnlyMDDateString(attendanceDO.getDate()));
			historyAttendanceDataVOS.add(historyAttendanceDataVO);
		}

		return JSONObject.toJSONString(historyAttendanceDataVOS);
	}

	/**
	 * 获取用户指定某天的考勤数据
	 * @param request
	 * @param date
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	@PostMapping("/attendance/history/oneday")
	@ResponseBody
	public String getOneDayAttendanceData(HttpServletRequest request, @RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
		String uid = ProjectUtils.getUidFromSession(request);
		AttendanceDO attendanceDO = attendanceService.getOneDayAttendanceData(uid, date);
		return JSONObject.toJSONString(attendanceDO);
	}
}
