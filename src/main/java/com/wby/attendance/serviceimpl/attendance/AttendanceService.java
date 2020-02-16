package com.wby.attendance.serviceimpl.attendance;

import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.constants.AttendanceConstants;
import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;
import com.wby.attendance.mappers.certification.AttendanceMapper;
import com.wby.attendance.pojos.AttendanceDO;
import com.wby.attendance.pojos.json.NormalJsonMessage;
import com.wby.attendance.utils.ProjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname AttendanceService
 * @Author WangBoyi
 * @Date 2020-2-14 18:42
 * @Description 考勤Service
 * @Version 1.0.0
 **/
@Service
public class AttendanceService {

	@Autowired
	AttendanceMapper attendanceMapper;

	/**
	 * 考勤
	 * @param attendanceDO
	 * @return com.wby.attendance.pojos.json.NormalJsonMessage
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@Transactional
	public NormalJsonMessage makeAAttendance(AttendanceDO attendanceDO){
//		设定日期
		Date date = new Date();
		attendanceDO.setDate(date);
//		检查今天是否保存过，保存过则选择直接更新

		//		判断status，如果没有选择，则默认完成度为0
		if(StringUtils.isBlank(attendanceDO.getStatus())){
			attendanceDO.setStatus(AttendanceConstants.ATTENDANCE_VERY_BAD);
		}

//		已经保存过
		if(hasAttendance(attendanceDO.getuId(), date)){
			updateTodayAttendance(attendanceDO);
		}else{
			//      第一次保存
			//		指定uuid
			attendanceDO.setId(ProjectUtils.getUuid());
			insertTodayAttendance(attendanceDO);
		}

		return new NormalJsonMessage(NormalJsonMessageCode.SUCCESS.getCode(), AttendanceConstants.ATTENDANCE_SUCCESS);
	}

	/**
	 * 更新今天的考勤数据
	 * @param attendanceDO
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private void updateTodayAttendance(AttendanceDO attendanceDO){
		String formatDate = ProjectUtils.getOnedayFormatDateString(attendanceDO.getDate());
		attendanceMapper.updateAttendance(attendanceDO, formatDate);
	}
	/**
	 * 插入今天的考勤数据
	 * @param attendanceDO
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private void insertTodayAttendance(AttendanceDO attendanceDO){
		attendanceMapper.insertAttendance(attendanceDO);
	}

	/**
	 * 检查今天是否考勤
	 * @param uid
	 * @param date
	 * @return boolean true 已经考勤
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private boolean hasAttendance(String uid, Date date){
		String formatDate = ProjectUtils.getOnedayFormatDateString(date);
		Integer count = attendanceMapper.countAttendanceByUserAndDate(uid, formatDate);
		return !(count != null && count < 1);
	}

	/**
	 * 获取今天的考勤数据
	 * @param uid
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public String getTodayAttendanceData(String uid){
		String formatDate = ProjectUtils.getTodayFormatDateString();
		if(hasAttendance(uid, new Date())){
			AttendanceDO attendanceDO = attendanceMapper.getAttendanceDO(uid, formatDate);
			return JSONObject.toJSONString(attendanceDO);
		}else{
			return JSONObject.toJSONString(new NormalJsonMessage(NormalJsonMessageCode.FAIL.getCode(), NormalJsonMessageConstants.TODAY_NOT_SIGN));
		}
	}
}
