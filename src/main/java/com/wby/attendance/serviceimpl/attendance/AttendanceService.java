package com.wby.attendance.serviceimpl.attendance;

import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.constants.AttendanceConstants;
import com.wby.attendance.constants.DateConstants;
import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;
import com.wby.attendance.mappers.certification.AttendanceMapper;
import com.wby.attendance.pojos.AttendanceDO;
import com.wby.attendance.pojos.AttendanceDTO;
import com.wby.attendance.pojos.json.NormalJsonMessage;
import com.wby.attendance.utils.ProjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
	 * @param attendanceDTO
	 * @return com.wby.attendance.pojos.json.NormalJsonMessage
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@Transactional
	public NormalJsonMessage makeAAttendance(AttendanceDTO attendanceDTO){
//		设定日期, 前端未设置则设置为今天
		if(attendanceDTO.getDate() == null){
			attendanceDTO.setDate(ProjectUtils.getTodayFormatDateString());
		}

		//		检查是否保存过，保存过则选择直接更新
		//		判断status，如果没有选择，则默认完成度为0
		if(StringUtils.isBlank(attendanceDTO.getStatus())){
			attendanceDTO.setStatus(AttendanceConstants.ATTENDANCE_VERY_BAD);
		}

//		已经保存过
		if(hasAttendance(attendanceDTO.getuId(), attendanceDTO.getDate())){
			updateTodayAttendance(attendanceDTO);
		}else{
			//      第一次保存
			//		指定uuid
			attendanceDTO.setId(ProjectUtils.getUuid());
			insertTodayAttendance(attendanceDTO);
		}

		return new NormalJsonMessage(NormalJsonMessageCode.SUCCESS.getCode(), AttendanceConstants.ATTENDANCE_SUCCESS);
	}

	/**
	 * 更新今天的考勤数据
	 * @param attendanceDTO
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private void updateTodayAttendance(AttendanceDTO attendanceDTO){
		attendanceMapper.updateAttendance(attendanceDTO);
	}
	/**
	 * 插入今天的考勤数据
	 * @param attendanceDTO
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private void insertTodayAttendance(AttendanceDTO attendanceDTO){
		attendanceMapper.insertAttendance(attendanceDTO);
	}

	/**
	 * 检查某天是否考勤
	 * @param uid
	 * @param date
	 * @return boolean true 已经考勤
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private boolean hasAttendance(String uid, String date){
		Integer count = attendanceMapper.countAttendanceByUserAndDate(uid, date);
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
		if(hasAttendance(uid, ProjectUtils.getTodayFormatDateString())){
			AttendanceDO attendanceDO = attendanceMapper.getAttendanceDO(uid, formatDate);
			return JSONObject.toJSONString(attendanceDO);
		}else{
			return JSONObject.toJSONString(new NormalJsonMessage(NormalJsonMessageCode.FAIL.getCode(), NormalJsonMessageConstants.TODAY_NOT_SIGN));
		}
	}

	/**
	 * 获取指定时间段的考勤数据
	 * @param uid
	 * @param from
	 * @param to
	 * @return java.util.List<com.wby.attendance.pojos.AttendanceDO>
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	public List<AttendanceDO> getAttendanceHistory(String uid, Date from, Date to){
		String fromDate = DateConstants.OLD_DATE_STRING, toDate = ProjectUtils.getTodayFormatDateString();

		if(from != null){
			fromDate = ProjectUtils.getOnedayFormatDateString(from);
		}

		if(to != null){
			toDate = ProjectUtils.getOnedayFormatDateString(to);
		}

		List<AttendanceDO> attendanceDOs = attendanceMapper.getAttendanceHistory(uid, fromDate, toDate, AttendanceConstants.HISTORY_DAY_MAX);

		return attendanceDOs;
	}

	/**
	 * 获取某一天考勤的数据
	 * @param uid
	 * @param date
	 * @return com.wby.attendance.pojos.AttendanceDO
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	public AttendanceDO getOneDayAttendanceData(String uid, Date date) {
		return attendanceMapper.getAttendanceDO(uid, ProjectUtils.getOnedayFormatDateString(date));
	}


}
