package com.wby.attendance.utils;

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
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat getSimpleDateFormat() {
		return sdf;
	}

	public static String getTodayFormatDateString() {
		return sdf.format(new Date());
	}

	public static String getOnedayFormatDateString(Date date) {
		return sdf.format(date);
	}

}
