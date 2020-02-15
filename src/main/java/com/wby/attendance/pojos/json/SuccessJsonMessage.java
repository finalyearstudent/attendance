package com.wby.attendance.pojos.json;

import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname SuccessJsonMessage
 * @Author WangBoyi
 * @Date 2020-2-14 16:08
 * @Description 普通成功消息
 * @Version 1.0.0
 **/
public class SuccessJsonMessage extends NormalJsonMessage{
	public SuccessJsonMessage() {
		super(NormalJsonMessageCode.SUCCESS.getCode(), NormalJsonMessageConstants.SUCCESS);
	}
}
