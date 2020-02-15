package com.wby.attendance.pojos.json;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname MsgObject
 * @Author WangBoyi
 * @Date 2020-2-14 12:06
 * @Description 返回前端json通用模板
 * @Version 1.0.0
 **/
public class NormalJsonMessage {
	/**
	 * 消息识别号
	 */
	String code;

	/**
	 * 消息通知内容
	 */
	String info;

	public NormalJsonMessage() {
	}

	public NormalJsonMessage(String code, String message) {
		this.code = code;
		this.info = message;
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
