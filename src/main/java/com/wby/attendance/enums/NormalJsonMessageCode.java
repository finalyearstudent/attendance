package com.wby.attendance.enums;


/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname NormalJsonMessageCode
 * @Author WangBoyi
 * @Date 2020-2-14 12:08
 * @Description 普通json消息模板识别号
 * @Version 1.0.0
 **/
public enum NormalJsonMessageCode {
	SUCCESS("0"), ERROR("1"), FAIL("2");
	String code;

	NormalJsonMessageCode(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
