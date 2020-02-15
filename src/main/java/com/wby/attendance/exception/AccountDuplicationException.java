package com.wby.attendance.exception;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname AccountDuplicateException
 * @Author WangBoyi
 * @Date 2020-2-14 11:56
 * @Description 用户名已存在异常
 * @Version 1.0.0
 **/
public class AccountDuplicationException extends Exception {
	@Override
	public String toString(){
		return "AccountDuplicationException: 用户名已存在";
	}
}
