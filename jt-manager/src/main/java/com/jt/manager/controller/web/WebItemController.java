package com.jt.manager.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manager.pojo.Item;
import com.jt.manager.pojo.ItemDesc;
import com.jt.manager.service.ItemService;

@Controller
@RequestMapping("/web/item/")
public class WebItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(Long itemId) {
		return itemService.findItemById(itemId);
	}
	
	@RequestMapping("/findItemDescById")
	@ResponseBody
	public ItemDesc findItemDescById(Long itemId) {
		return itemService.findItemDescById(itemId);
	}
}
