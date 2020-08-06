package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private JedisCluster jedisCluster ;
	
	@RequestMapping("/{moduleName}")
	public String userModule(@PathVariable String moduleName) {
		return moduleName;
	}
	
	
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult doRegister(User user) {
		try {
			userService.save(user);
			return SysResult.oK();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "注册失败");
	}
 
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response) {
		try{
			//判断登录是否有效
			    String token=userService.findUserByUP(user);
				if(StringUtils.isEmpty(token)) {
					return SysResult.build(201, "登录失败");
				}
			//将token数据保存到cookie
				Cookie cookie=new Cookie("JT_TICKET", token);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
			    response.addCookie(cookie);
				return SysResult.oK();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "登录失败");
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String token=null;
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token=cookie.getValue();
				break;
			} 
		}
		jedisCluster.del(token);
		Cookie cookie =new Cookie("JT_TICKET","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/index.html";
	}
}
