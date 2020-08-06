package com.jt.manager.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manager.pojo.User;

@Controller
@RequestMapping("/web")
public class WebJsonPController {
	
	@RequestMapping(value = "/testJSONP" )
	@ResponseBody
	public MappingJacksonValue jsonpSuper(  String callback) {
		User user=new User();
		user.setId(1);
		user.setName("tomcat");
		MappingJacksonValue value=new MappingJacksonValue(user);
		value.setJsonpFunction(callback);
		return value;
	}

}
