package com.wby.attendance.serviceimpl.certification;

import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.constants.SessionConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;
import com.wby.attendance.pojos.json.NormalJsonMessage;
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
	 * @param uid
	 * @return void
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.1
	 **/
	public void setLoginUserFlag(HttpServletRequest request, HttpServletResponse response, String uid){
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstants.LOGIN_USER, uid);
		session.setMaxInactiveInterval(60 * 15);
		Cookie cookie  = new Cookie(SessionConstants.LOGIN_USER, uid);
		cookie.setMaxAge(60 * 15);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @return com.wby.attendance.pojos.json.NormalJsonMessage
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.1.0
	 **/
	public NormalJsonMessage logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConstants.LOGIN_USER);
		return new NormalJsonMessage(NormalJsonMessageCode.SUCCESS.getCode(), NormalJsonMessageConstants.LOG_OUT_SUCCESS);
	}
}
