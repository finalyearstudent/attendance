package com.wby.attendance.pojos;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname UserDTO
 * @Author WangBoyi
 * @Date 2020-2-14 10:18
 * @Description User的数据传输类
 * @Version 1.0.0
 **/
public class UserDTO {
	/** UUID 主键*/
	String id;

	/** 账号 加密**/
	String account;

	/** 密码 加密**/
	String password;

	/** 当前状态*/
	String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
