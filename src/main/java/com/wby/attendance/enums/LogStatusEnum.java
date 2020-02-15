package com.wby.attendance.enums;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname LogStatusEnum
 * @Author WangBoyi
 * @Date 2020-2-14 9:58
 * @Description 记录登录状态
 * @Version 1.0.0
 **/
public enum LogStatusEnum {
	OFFLINE("0"), ONLINE("1"), BUSY("2"), LONELY("3");

	String status;

	LogStatusEnum(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
