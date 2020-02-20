package com.wby.attendance.utils;

import org.springframework.util.DigestUtils;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname PassWordUtils
 * @Author WangBoyi
 * @Date 2020-2-14 11:10
 * @Description 密码工具类
 * @Version 1.0.0
 **/
public class PassWordUtils {
	/**
	 * MD5加密
	 * @param str
	 * @return
	 */
	public static String MD5Encode(String str){
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

}
