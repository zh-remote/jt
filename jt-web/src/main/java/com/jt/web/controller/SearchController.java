package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.search.pojo.Item;
import com.jt.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	
    @RequestMapping(value = "/search",produces="text/html;charset=utf-8")
	public String search(String q,Model model) {
		 System.out.println("搜索系统"+q);
		 List<Item> result = searchService.findItemByKey(q);
		 model.addAttribute("itemList", result);
		return "search";
	}
}
