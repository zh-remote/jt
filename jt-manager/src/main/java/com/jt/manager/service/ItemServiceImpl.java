package com.jt.manager.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.EasyUIResult;
import com.jt.manager.mapper.ItemDescMapper;
import com.jt.manager.mapper.ItemMapper;
import com.jt.manager.pojo.Item;
import com.jt.manager.pojo.ItemDesc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
//	private Jedis redisService ;
//    private RedisService redisService;
	private JedisCluster jedisCluster;
	
	private static final ObjectMapper   objectMapper=new ObjectMapper();
	
	public EasyUIResult findItemByPage(int page,int rows) {
//		int total=itemMapper.findCount();
		int total = itemMapper.selectCount(null);
		
		int startIndex=(page-1)*rows;
		List<Item> items = itemMapper.findItemByPage(startIndex,rows);
		EasyUIResult result = new EasyUIResult(total,items);
		return result;
	}

	@Override
	public String findItemNameById(Long itemId) {
		
		return  itemMapper.findItemNameById(itemId);
	}

	@Override
	public void saveItem(Item item,String itemDesc) {
		
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		
		ItemDesc  desc=new ItemDesc();
		desc.setItemId(item.getId());
		desc.setItemDesc(itemDesc);
		desc.setCreated(item.getCreated());
		desc.setUpdated(item.getCreated());
		itemDescMapper.insert(desc);
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setUpdated(new Date());
		//动态更新，item中不为空的数据更新到数据库里
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateByPrimaryKey(itemDesc);
		itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public void deleteItems(Long[] ids) {
		//先删除从表，后删除主表
		itemDescMapper.deleteByIDS(ids);
		itemMapper.deleteByIDS(ids);
	}

	@Override
	public void updateItemsStatus(Long[] ids, Integer status) {
		itemMapper.updateStatus(ids,status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public EasyUIResult findItemPageByCache(int page, int rows) {
		String key="ITEM_PAGE_"+page+"_"+rows;
		EasyUIResult result = null;
		try {
			String resultJSON = jedisCluster.get(key);
			if(StringUtils.isEmpty(resultJSON)) {
				result=findItemByPage(page,rows);
				jedisCluster.set(key,objectMapper.writeValueAsString(result));
				System.out.println("添加商品页到缓存++++++++++++++++++");
			}else {
				result=
				objectMapper.readValue(resultJSON, EasyUIResult.class);
				System.out.println("从缓存中查找商品页信息");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Item findItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

 
}
