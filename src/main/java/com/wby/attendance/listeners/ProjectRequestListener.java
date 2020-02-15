package com.wby.attendance.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname ProjectRequestListener
 * @Author WangBoyi
 * @Date 2020-2-15 15:51
 * @Description 监听request
 * @Version 1.0.0
 **/
@WebListener
public class ProjectRequestListener implements ServletRequestListener {
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	public void requestInitialized(ServletRequestEvent sre) {

	}
}
