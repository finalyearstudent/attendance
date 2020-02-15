package com.wby.attendance.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname RequestListener
 * @Author WangBoyi
 * @Date 2020-2-15 15:40
 * @Description 监听每个session的创立
 * @Version 1.0.0
 **/
@WebListener
public class ProjectSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	}
}
