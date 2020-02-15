package com.wby.attendance.controllers;

import com.alibaba.fastjson.JSONObject;
import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.constants.SessionConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;
import com.wby.attendance.exception.AccountDuplicationException;
import com.wby.attendance.pojos.UserDO;
import com.wby.attendance.pojos.UserDTO;
import com.wby.attendance.pojos.json.NormalJsonMessage;
import com.wby.attendance.pojos.json.SuccessJsonMessage;
import com.wby.attendance.serviceimpl.certification.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname RegisterController
 * @Author WangBoyi
 * @Date 2020-2-14 11:01
 * @Description 用户注册请求处理
 * @Version 1.0.0
 **/
@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService registerService;

	/**
	 * 接受注册参数
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/newaccount")
	public String register(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response){
		UserDO userDO;
		try {
			userDO = registerService.register(userDTO);
		}catch (AccountDuplicationException e){
			return JSONObject.toJSONString(new NormalJsonMessage(NormalJsonMessageCode.ERROR.getCode()
					, NormalJsonMessageConstants.ACCOUNT_DUPLICATION));
		}

//		注册成功，设置cookie
		String account = userDTO.getAccount();
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstants.LOGIN_USER, account);
		session.setMaxInactiveInterval(60 * 15);
		Cookie cookie  = new Cookie(SessionConstants.LOGIN_USER, account);
		cookie.setMaxAge(60 * 15);
		cookie.setPath("/");
		response.addCookie(cookie);

		return JSONObject.toJSONString(new SuccessJsonMessage());
	}

}
