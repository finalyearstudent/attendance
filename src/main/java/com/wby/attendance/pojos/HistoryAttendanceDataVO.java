package com.wby.attendance.pojos;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname HistoryAttendanceDataVO
 * @Author WangBoyi
 * @Date 2020-2-16 13:36
 * @Description 返回给前端的历史数据VO
 * @Version 1.1.0
 **/
public class HistoryAttendanceDataVO {
	String date;

	String status;

	String task;

	String activity;

	public HistoryAttendanceDataVO(String date, String status, String task, String activity) {
		this.date = date;
		this.status = status;
		this.task = task;
		this.activity = activity;
	}

	public HistoryAttendanceDataVO() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
