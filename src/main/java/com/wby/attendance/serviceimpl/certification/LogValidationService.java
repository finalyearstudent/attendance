package com.wby.attendance.serviceimpl.certification;

import com.wby.attendance.constants.ValidationConstants;
import com.wby.attendance.enums.TrueAndFalseEnum;
import com.wby.attendance.mappers.certification.UserMapper;
import com.wby.attendance.pojos.UserDO;
import com.wby.attendance.pojos.UserDTO;
import com.wby.attendance.pojos.util.ValidationReturnObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpSession;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname LogValidationService
 * @Author WangBoyi
 * @Date 2020-2-14 9:24
 * @Description 对登录的账号进行密码验证
 * @Version 1.0.0
 **/
@Service
@RequestScope
public class LogValidationService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 验证登录
	 * @param userDTO
	 * @return com.wby.attendance.pojos.util.ValidationReturnObject
	 * @date 2020-2-16
	 * @author WangBoyi
	 * @version 1.0.0
	 **/
	public ValidationReturnObject validate(UserDTO userDTO) {

		String account = userDTO.getAccount();

		if(StringUtils.isBlank(account)){
			return new ValidationReturnObject(TrueAndFalseEnum.FALSE.getCode(), ValidationConstants.USER_IS_BLANK);
		}

		UserDO userDO = userMapper.findAUserByAccount(account);

		if(userDO == null){
			return new ValidationReturnObject(TrueAndFalseEnum.FALSE.getCode(), ValidationConstants.USER_NOT_EXIST);
		}

		if(StringUtils.equals(userDO.getPassword(), userDTO.getPassword())){
			return new ValidationReturnObject(TrueAndFalseEnum.TRUE.getCode(), ValidationConstants.SUCCESS);
		}

		return new ValidationReturnObject(TrueAndFalseEnum.FALSE.getCode(), ValidationConstants.ACCOUNT_OR_PASSWORD_ERROR);
	}

}
