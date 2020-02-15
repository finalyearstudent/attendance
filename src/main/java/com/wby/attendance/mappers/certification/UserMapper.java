package com.wby.attendance.mappers.certification;

import com.wby.attendance.pojos.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname UserMapper
 * @Author WangBoyi
 * @Date 2020-2-14 9:31
 * @Description 负责管理关于用户的数据
 * @Version 1.0.0
 **/
@Mapper
public interface UserMapper {
	/**
	 * 根据用户名查找用户
	 * @return UserDO 一个用户对象
	 */
	@Select("select c_id as id, c_account as account, c_password as password, c_status as status from attendance.t_user where c_account = #{account}")
	public UserDO findAUserByAccount(String account);

	/**
	 * 插入一个用户
	 * @param userDO
	 */
	@Insert("INSERT INTO `attendance`.`t_user` (`c_id`, `c_account`, `c_password`, `c_status`) VALUES (#{userDO.id}, #{userDO.account}, #{userDO.password}, #{userDO.status})")
	public void insertUser(@Param("userDO") UserDO userDO);

	/**
	 * 统计即将添加的账号相同的存在个数
	 * @param account 账号名
	 * @return Integer
	 */
	@Select("select count(c_account) from attendance.t_user where c_account = #{newAccount}")
	public Integer countAccounts(@Param("newAccount")String account);
}
