package com.jt.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.pojo.User;
import com.jt.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

public class UserInterceptor implements HandlerInterceptor {

	
	@Autowired
	private JedisCluster JedisCluster;
	
	private final ObjectMapper objectMapper =new ObjectMapper();
	
	/**
	 * 在业务逻辑前拦截
	 * 1.判断cookie是否有token
	 * 2.根据token查询redis受否存在该数据
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取cookie
		Cookie[] cookies = request.getCookies();
		String token=null;
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				//获取令牌
				token=cookie.getValue();
			}
		}
		
		if(!StringUtils.isEmpty(token)) {
			String userJosn = JedisCluster.get(token);
			if(!StringUtils.isEmpty(userJosn)) {
				//将用户数据存入到threadlocal中
				UserThreadLocal.set(objectMapper.readValue(userJosn, User.class));
				//数据存在 放行
				return true;
			}
		}
		//用户未登录 重定向到登录页面
		response.sendRedirect("/user/login.html");
		return false;
	}

	/**
	 * 在业务完成后执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 在返回数据给前台前执行 ==最后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//关闭本地线程
		UserThreadLocal.remove();
	}

}
