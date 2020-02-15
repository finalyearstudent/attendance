package com.wby.attendance.pojos.util;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname ValidationReturnObject
 * @Author WangBoyi
 * @Date 2020-2-14 17:26
 * @Description 丰富验证返回结果
 * @Version 1.0.0
 **/
public class ValidationReturnObject {
	/**
	 * 验证返回的验证号
	 */
	String code;

	/**
	 * 验证返回附带的信息
	 */
	String info;

	public ValidationReturnObject(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
