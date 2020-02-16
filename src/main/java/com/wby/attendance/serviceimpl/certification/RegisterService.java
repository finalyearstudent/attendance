package com.wby.attendance.serviceimpl.certification;

import com.wby.attendance.enums.LogStatusEnum;
import com.wby.attendance.exception.AccountDuplicationException;
import com.wby.attendance.mappers.certification.UserMapper;
import com.wby.attendance.pojos.UserDO;
import com.wby.attendance.pojos.UserDTO;
import com.wby.attendance.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname RegisterService
 * @Author WangBoyi
 * @Date 2020-2-14 11:06
 * @Description 用户注册service
 * @Version 1.0.0
 **/
@Service
public class RegisterService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 注册账号
	 * @param userDTO
	 * @return com.wby.attendance.pojos.UserDO
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	@Transactional
	public UserDO register(UserDTO userDTO) throws AccountDuplicationException{
		//用户名重复，抛出异常
		if(!checkAccountUnique(userDTO.getAccount())){
			throw new AccountDuplicationException();
		}

		userDTO.setStatus(LogStatusEnum.OFFLINE.getStatus());
		userDTO.setId(ProjectUtils.getUuid());
		UserDO userDO = new UserDO(userDTO.getId(), userDTO.getAccount(), userDTO.getPassword(), userDTO.getStatus());
		userMapper.insertUser(userDO);
		return userDO;
	}

	/**
	 * 验证账号是否已存在
	 * @param account
	 * @return boolean
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	private boolean checkAccountUnique(String account){
		return userMapper.countAccounts(account) == 0 ? true : false;
	}

}
