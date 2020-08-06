package com.jt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manager.service.ItemCatService;
import com.jt.vo.EasyUITree;

@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatList(@RequestParam(value = "id",defaultValue="0")Long parentId){
		return itemCatService.findItemCatCache(parentId);
	} 
}
