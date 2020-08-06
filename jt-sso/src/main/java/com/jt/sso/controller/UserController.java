package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue checkUser(
			@PathVariable String param,
			@PathVariable String type,
			  String callback) {
		boolean flag=userService.findCheckUser(param,type);
		MappingJacksonValue value=new MappingJacksonValue(SysResult.oK(flag));
		value.setJsonpFunction(callback);
		return value;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public SysResult register(User user) {
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "新增失败");
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public SysResult findUserByUP(User user) {
		try {
			String token=userService.findUserByUP(user);
			if(StringUtils.isEmpty(token)) {
				return SysResult.build(201, "用户查询失败");
			}
			return SysResult.oK(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户查询失败");
	}

	@RequestMapping("/query/{token}")
	@ResponseBody
	public MappingJacksonValue findUserByTicket(@PathVariable String token,String  callback) {
		String userJson = jedisCluster.get(token);
		MappingJacksonValue value= null;
		if(!StringUtils.isEmpty(userJson)) {
			 value =new MappingJacksonValue(SysResult.oK(userJson));
			 value.setJsonpFunction(callback);
		}else {
			value =new MappingJacksonValue(SysResult.build(201, "用户查询失败"));
			value.setJsonpFunction(callback);
		}
		return value;
	}
}
