package com.wby.attendance.pojos.json;

import com.wby.attendance.constants.NormalJsonMessageConstants;
import com.wby.attendance.enums.NormalJsonMessageCode;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname FailJsonMessage
 * @Author WangBoyi
 * @Date 2020-2-14 16:13
 * @Description 普通失败消息
 * @Version 1.0.0
 **/
public class FailJsonMessage extends NormalJsonMessage {
	public FailJsonMessage(){
		super(NormalJsonMessageCode.FAIL.getCode(), NormalJsonMessageConstants.FAIL);
	}
}
