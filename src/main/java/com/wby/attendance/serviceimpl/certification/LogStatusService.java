package com.wby.attendance.serviceimpl.certification;

import com.wby.attendance.constants.SessionConstants;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname LogStatusService
 * @Author WangBoyi
 * @Date 2020-2-16 9:35
 * @Description 处理session和cookie
 * @Version 1.0.1
 **/
@Service
public class LogStatusService {

	/**
	 * 设置session和cookie为登录状态
	 * @param request
	 * @param response
	 * @param account
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.1
	 **/
	public void setLoginUserFlag(HttpServletRequest request, HttpServletResponse response, String account){
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstants.LOGIN_USER, account);
		session.setMaxInactiveInterval(60 * 15);
		Cookie cookie  = new Cookie(SessionConstants.LOGIN_USER, account);
		cookie.setMaxAge(60 * 15);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
