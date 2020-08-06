package com.jt.web.service;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private HttpClientService httpClient;
	
	private static final ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public Item findItemById(Long itemId) {
		String url="http://manager.jt.com/web/item/findItemById";
		Item item=null;
		try {
			Map<String, String> params=new HashedMap();
			params.put("itemId", itemId+"");
			String resultJSON = httpClient.doPost(url,params);
		    item = objectMapper.readValue(resultJSON, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return item;
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url="http://manager.jt.com/web/item/findItemDescById";
		ItemDesc itemDesc=null;
		try {
			Map<String, String> params=new HashedMap();
			params.put("itemId", itemId+"");
			String resultJSON = httpClient.doPost(url,params);
			itemDesc = objectMapper.readValue(resultJSON, ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return itemDesc;
	}

}
