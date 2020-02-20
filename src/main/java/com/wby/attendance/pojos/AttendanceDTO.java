package com.wby.attendance.pojos;


/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname AttendanceDTO
 * @Author WangBoyi
 * @Date 2020-2-16 17:58
 * @Description AttendanceDTO
 * @Version 1.1.0
 **/
public class AttendanceDTO {
	/**
	 * 打卡id
	 */
	String id;

	/**
	 * 今日任务完成度
	 */
	String status;

	/**
	 * 用户id
	 */
	String uId;

	/**
	 * 今日任务
	 */
	String task;

	/**
	 * 实际完成
	 */
	String activity;

	/**
	 * 打卡日期
	 */
	String date;

	public AttendanceDTO() {
	}

	public AttendanceDTO(String id, String status, String uId, String task, String activity, String date) {
		this.id = id;
		this.status = status;
		this.uId = uId;
		this.task = task;
		this.activity = activity;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
