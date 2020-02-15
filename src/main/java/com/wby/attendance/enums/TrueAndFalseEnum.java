package com.wby.attendance.enums;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname TrueAndFalse
 * @Author WangBoyi
 * @Date 2020-2-14 17:29
 * @Description true 和 false的枚举
 * @Version 1.0.0
 **/
public enum TrueAndFalseEnum {
	TRUE("0"), FALSE("1");
	String code;

	TrueAndFalseEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
