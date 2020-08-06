package com.jt.manager.service;
/**
 * 商品业务层
 * @author asus
 *
 */

import com.jt.common.vo.EasyUIResult;
import com.jt.manager.pojo.Item;
import com.jt.manager.pojo.ItemDesc;

public interface ItemService {

	EasyUIResult findItemByPage(int page,int rows);
	
	/**
	 * 根据id查询
	 * @param itemId
	 * @return
	 */
	String findItemNameById(Long itemId);

	/**
	 * 保存商品
	 * @param item
	 */
	void saveItem(Item item,String itemDesc);

	/**
	 * 更新商品
	 * @param item
	 * @param desc 
	 */
	void updateItem(Item item, String desc);

	/**
	 * 删除商品根据多个id
	 * @param ids
	 */
	void deleteItems(Long[] ids);

	/**
	 * 商品上下架
	 * @param ids 
	 * @param status
	 */
	void updateItemsStatus(Long[] ids, Integer status);

	ItemDesc findItemDescById(Long itemId);
    /**
     * 添加了缓存功能
     * @param page
     * @param rows
     * @return
     */
	EasyUIResult findItemPageByCache(int page, int rows);

	/**
	 * 根据id查询商品
	 * @param itemId
	 * @return
	 */
	Item findItemById(Long itemId);

 
}
