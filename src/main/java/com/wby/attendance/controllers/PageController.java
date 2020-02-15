package com.wby.attendance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@RequestMapping("/")
	public String defaultPage() {
		return "attendance";
	}

	@RequestMapping("/index")
	public String index() {
//		这里直接返回字符串相当于请求转发为了/index.suffix
		return "attendance";
	}

	@RequestMapping("/attendance")
	public String attendance() {
		return "attendance";
	}

	@RequestMapping("/version")
	public String version() {
		return "version";
	}

	@RequestMapping("/log")
	public String logPage() {
		return "log";
	}

}
