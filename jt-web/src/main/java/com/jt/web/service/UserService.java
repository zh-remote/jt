package com.jt.web.service;

import com.jt.web.pojo.User;

public interface UserService {

	void save(User user);

	String findUserByUP(User user);

	
}
