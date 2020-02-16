package com.wby.attendance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname IndexController
 * @Author WangBoyi
 * @Date 2020-2-14 12:15
 * @Description 主页Controller
 * @Version 1.0.0
 **/
@Controller
public class PageController {

	/**
	 * 返回默认页面
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version todo
	 **/
	@RequestMapping("/")
	public String defaultPage() {
		return "attendance";
	}

	/**
	 * 返回首页（默认页面）
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@RequestMapping("/index")
	public String index() {
//		这里直接返回字符串相当于请求转发为了/index.suffix
		return "attendance";
	}

	/**
	 * 返回考勤页面
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@RequestMapping("/attendance")
	public String attendance() {
		return "attendance";
	}

	/**
	 * 返回历史版本页面
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@RequestMapping("/version")
	public String version() {
		return "version";
	}

	/**
	 * 登录页面
	 *
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@RequestMapping("/log")
	public String logPage() {
		return "log";
	}

}
