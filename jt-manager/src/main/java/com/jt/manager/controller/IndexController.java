package com.jt.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public  String index() {
		return "index";
	}
	/**
	 * 通用的页面跳转
	 * @param module
	 * @return
	 */
	@RequestMapping("/page/{module}")
	public String module(@PathVariable String module) {
		return module;
	}
}
