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
import com.wby.attendance.serviceimpl.certification.LogStatusService;
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

	@Autowired
	LogStatusService logStatusService;

	/**
	 * 注册账号
	 * @param userDTO
	 * @param request
	 * @param response
	 * @return java.lang.String
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@PostMapping("/newaccount")
	public String register(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response){
		UserDO userDO;
		try {
			userDO = registerService.register(userDTO);
		}catch (AccountDuplicationException e){
			return JSONObject.toJSONString(new NormalJsonMessage(NormalJsonMessageCode.ERROR.getCode()
					, NormalJsonMessageConstants.ACCOUNT_DUPLICATION));
		}

		logStatusService.setLoginUserFlag(request, response, userDTO.getId());

		return JSONObject.toJSONString(new SuccessJsonMessage());
	}

}
