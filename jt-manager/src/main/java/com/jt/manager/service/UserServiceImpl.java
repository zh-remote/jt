package com.jt.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manager.mapper.UserMapper;
import com.jt.manager.pojo.User;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAll() {
		List<User> users = userMapper.findAll();
		return users;
	}
	


}
