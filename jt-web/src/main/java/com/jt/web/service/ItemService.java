package com.jt.web.service;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

public interface ItemService {

	/**
	 * 根据id查询商品xinxi
	 * @param itemId 商品id
	 * @return 
	 */
	Item findItemById(Long itemId);

	/**
	 * 查询商品详细
	 * @param itemId
	 * @return
	 */
	ItemDesc findItemDescById(Long itemId);

 
}
