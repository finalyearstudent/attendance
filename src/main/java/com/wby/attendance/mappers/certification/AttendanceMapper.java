package com.wby.attendance.mappers.certification;

import com.wby.attendance.pojos.AttendanceDO;
import com.wby.attendance.pojos.AttendanceDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname AttendanceMapper
 * @Author WangBoyi
 * @Date 2020-2-14 19:04
 * @Description AttendanceMapper
 * @Version 1.0.0
 **/
@Mapper
public interface AttendanceMapper {

	@Insert("INSERT INTO `attendance`.`t_attendance` (`c_id`, `c_status`, `c_uid`, `c_task`, `c_activity`, `d_date`) VALUES" +
			" (#{attendanceDO.id}, #{attendanceDO.status}, #{attendanceDO.uId}, #{attendanceDO.task}, #{attendanceDO.activity}, #{attendanceDO.date})")
	public void insertAttendance(@Param("attendanceDO") AttendanceDTO attendanceDO);

	@Update("update t_attendance set c_status = #{attendanceDO.status}, c_task = #{attendanceDO.task}," +
			"c_activity = #{attendanceDO.activity}, d_date = #{attendanceDO.date} where c_uid = #{attendanceDO.uId} " +
			"and d_date = #{attendanceDO.date}")
	public void updateAttendance(@Param("attendanceDO") AttendanceDTO attendanceDO);

	@Select("select count(c_id) from t_attendance where c_uid = #{uid} and d_date = #{date}")
	public Integer countAttendanceByUserAndDate(@Param("uid") String uid,@Param("date") String date);

	@Select("select c_id as id, c_uid as uId, c_status as status, d_date as date, c_task as task," +
			"c_activity as activity from attendance.t_attendance where c_uid = #{uid} and d_date = #{date}")
	public AttendanceDO getAttendanceDO(@Param("uid") String uid,@Param("date") String todayFormatDateString);

	@Select("select c_id as id, c_uid as uId, c_status as status, d_date as date, c_task as task," +
			"c_activity as activity  from attendance.t_attendance where c_uid = #{uid} and d_date between #{fd} and #{td} ORDER BY d_date limit #{num};")
	List<AttendanceDO> getAttendanceHistory(@Param("uid") String uid,@Param("fd") String fromDate,@Param("td") String toDate, int num);

}
