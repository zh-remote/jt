package com.jt.sso.service;

import com.jt.sso.pojo.User;

public interface UserService {

	/**
	 * 检查用户名\手机号\邮箱是否已注册
	 * @param param 检查参数
	 * @param type  参数类型1用户名，2手机号，3邮箱
	 * @return
	 */
	boolean findCheckUser(String param, String type);

	/**
	 * 保存用户信息
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * sso单点登录
	 * @param user
	 * @return
	 */
	String findUserByUP(User user);

}
