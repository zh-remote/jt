package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserServiecImpl implements UserService {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper objectMapper=new ObjectMapper();

	@Override
	public void save(User user) {
		String url="http://sso.jt.com/user/register";
		Map<String, String> params=new HashedMap();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		String result = httpClientService.doPost(url,params);
		try {
			SysResult sysResult = objectMapper.readValue(result, SysResult.class);
			if(!(sysResult.getStatus()==200)) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 将数据传递到sso服务器验证，验证成功后返回
	 */
	@Override
	public String findUserByUP(User user) {
		String url="http://sso.jt.com/user/login";
		String token=null;
		Map<String, String> params=new HashMap<String, String>();
		params.put("username",user.getUsername());
		params.put("password",user.getPassword());
		String resultJSON = httpClientService.doPost(url,params);
		try {
			SysResult sysResult = objectMapper.readValue(resultJSON, SysResult.class);
			if(sysResult.getStatus()!=200) {
				throw new RuntimeException();
			}
		   token = (String) sysResult.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return token;
	}

}
