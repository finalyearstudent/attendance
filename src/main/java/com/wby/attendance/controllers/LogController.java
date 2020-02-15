package com.wby.attendance.controllers;


import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.constants.SessionConstants;
import com.wby.attendance.enums.TrueAndFalseEnum;
import com.wby.attendance.pojos.UserDTO;
import com.wby.attendance.pojos.util.ValidationReturnObject;
import com.wby.attendance.serviceimpl.certification.LogValidation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname LogController
 * @Author WangBoyi
 * @Date 2020-2-14 10:37
 * @Description 对用户登录的请求进行处理
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	LogValidation logValidation;

	@PostMapping("/user")
	@ResponseBody
	public String log(@RequestParam("account")String account, @RequestParam("password")String password, HttpServletRequest request, HttpServletResponse response){
		UserDTO userDTO = new UserDTO();
		userDTO.setAccount(account);
		userDTO.setPassword(password);
		logValidation.setUserDTO(userDTO);
		ValidationReturnObject validationReturnObject = logValidation.validate();
		if(StringUtils.equals(validationReturnObject.getCode(), TrueAndFalseEnum.TRUE.getCode())){
//			登录成功，设置session, cookie
			HttpSession session = request.getSession();
			session.setAttribute(SessionConstants.LOGIN_USER, account);
			session.setMaxInactiveInterval(60 * 15);
			Cookie cookie  = new Cookie(SessionConstants.LOGIN_USER, account);
			cookie.setMaxAge(60 * 15);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return JSONObject.toJSONString(validationReturnObject);
	}
}
