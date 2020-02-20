package com.wby.attendance.Interceptor;

import com.wby.attendance.constants.SessionConstants;
import com.wby.attendance.serviceimpl.certification.LogStatusService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname LogHandlerIntercepter
 * @Author WangBoyi
 * @Date 2020-2-15 16:30
 * @Description 拦截所有路径的请求，进行登录状态判断
 * @Version 1.0.0
 **/
public class LogHandlerIntercepor implements HandlerInterceptor {

	@Autowired
	LogStatusService logStatusService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		检查登录情况，未登录全部打回登录界面
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute(SessionConstants.LOGIN_USER);
		Cookie[] cookies = request.getCookies();

		if(cookies != null){
			for(Cookie cookie : cookies){
				if(StringUtils.equals(cookie.getName(), SessionConstants.LOGIN_USER)
						&& StringUtils.equals(cookie.getValue(), uid)){
//					重置session时间
					logStatusService.setLoginUserFlag(request, response, uid);
					return true;
				}
			}
		}

//		重定向
		response.sendRedirect("/log");
//		这里的请求转发造成了前端浏览器缓存不重新加载页面的问题
//		request.getRequestDispatcher("/log").forward(request,response);
		return false;
	}
}
