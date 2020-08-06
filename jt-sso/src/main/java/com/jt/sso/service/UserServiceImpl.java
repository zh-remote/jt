package com.jt.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisCluster jedisCluster;
	private static final ObjectMapper objectMapper=new ObjectMapper();
	@Override
	public boolean findCheckUser(String param, String type) {
		User user=new User();		
		switch (type) {
		case "1":
			user.setUsername(param);
			break;
		case "2":
			user.setPhone(param);
			break;
		case "3":
			user.setEmail(param);
			break;
		}
		int count = userMapper.selectCount(user);
		return count==0?false:true;//用户名，手机，邮箱是否存在 0不存在 
	}

	@Override
	public void saveUser(User user) {
		String md5Pass=DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Pass);
		user.setEmail(user.getPhone());
		user.setUpdated(new Date());
		user.setCreated(user.getUpdated());
		userMapper.insert(user);
	}

	@Override
	public String findUserByUP(User user) {
		//数据库是密文
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		User userDB=userMapper.findUserByUP(user);
		if(userDB==null) {
			throw new RuntimeException();
		}
		String token ="JT_TICKET_"+System.currentTimeMillis()+user.getUsername();
		String resultToken=DigestUtils.md5Hex(token);
		try {
			String userJson = objectMapper.writeValueAsString(userDB);
			jedisCluster.setex(resultToken, 3600*24*7, userJson);
			System.out.println("用户点单登录成功");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return resultToken;
	}
}
