package com.wby.attendance.utils;

import com.wby.attendance.constants.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname UuidUtils
 * @Author WangBoyi
 * @Date 2020-2-15 14:56
 * @Description 项目常用工具类
 * @Version 1.0.0
 **/
public class ProjectUtils {
	/**
	 * 获取uuid
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	public static SimpleDateFormat sdfOnlyMD = new SimpleDateFormat("MM/dd");

	/**
	 * 获取已经设置好格式的sdf
	 *
	 * @return java.text.SimpleDateFormat
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public static SimpleDateFormat getSimpleDateFormat() {
		return sdf;
	}

	/**
	 * 获取今天日期的字符串
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public static String getTodayFormatDateString() {
		return sdf.format(new Date());
	}

	/**
	 * 获取某一天的格式化日期字符串
	 * @param date
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public static String getOnedayFormatDateString(Date date) {
		return sdf.format(date);
	}

	/**
	 * 获取只有 月-日 格式的字符串
	 * @param date
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	public static String getOnlyMDDateString(Date date){
		return sdfOnlyMD.format(date);
	}
	/**
	 * 获取session中的uid
	 * @param request
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	public static String getUidFromSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute(SessionConstants.LOGIN_USER);
		return uid;
	}
}
