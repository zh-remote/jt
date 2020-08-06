package com.jt.manager.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manager.pojo.Item;
import com.jt.manager.pojo.ItemDesc;
import com.jt.manager.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	private static final Logger LOGGER= Logger.getLogger(ItemController.class) ;
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult findItemByPage(int page,int rows) {
		return itemService.findItemPageByCache(page, rows);
	}
	
	@RequestMapping(value = "/queryItemCatName",produces="text/html;charset=utf-8")//String的默认编码是ios8859-8
	@ResponseBody
	public String findItemNameById(Long itemCatId) {
		return itemService.findItemNameById(itemCatId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc) {
		try {
			System.out.println(desc);
			itemService.saveItem(item, desc);
			return SysResult.oK();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SysResult.build(201, "商品新增失败");
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc) {
		try {
			itemService.updateItem(item,desc);
			LOGGER.info("---------------------商品修改成功");
			return SysResult.oK();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return SysResult.build(201, "商品修改失败");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids) {
		try {
			itemService.deleteItems(ids);
			
			return SysResult.oK();
		} catch (Exception e) {
		}
		return SysResult.build(201, "商品删除失败");
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockItems(Long[] ids) {
		try {
			Integer status =2;
			itemService.updateItemsStatus(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
		}
		return SysResult.build(201, "商品上架失败");
	}
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelfItems(Long[] ids) {
		try {
			Integer status =1;
			itemService.updateItemsStatus(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
		}
		return SysResult.build(201, "商品下架失败");
	}
	
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId) {
		try {
			ItemDesc itemDesc =itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
		}
		return SysResult.build(201, "商品详情查询失败");
	}
}
