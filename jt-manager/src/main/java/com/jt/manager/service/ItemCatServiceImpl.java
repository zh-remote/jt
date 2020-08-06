package com.jt.manager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.manager.mapper.ItemCatMapper;
import com.jt.manager.pojo.ItemCat;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
	//private Jedis redisService ;
	//private RedisService redisService;
	private JedisCluster jedisCluster;
	private static final ObjectMapper   objectMapper=new ObjectMapper();
	
	@Override
	public List<EasyUITree> findItemCatList(Long parentId) {
		ItemCat itemCat =new ItemCat();
		itemCat.setParentId(parentId);
		List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
		List<EasyUITree> treeList=new ArrayList<EasyUITree>();
		for (ItemCat i : itemCatList) {
			EasyUITree easyUITree=new EasyUITree();
			easyUITree.setId(i.getId());
			easyUITree.setText(i.getName());
			String state =i.getIsParent()?"closed":"open";
			easyUITree.setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}

	@Override
	public List<EasyUITree> findItemCatCache(Long parentId) {
		String key="ITEM_CAT_"+parentId;
		List<EasyUITree> result=null;
		try {
			String resultJson = jedisCluster.get(key);
			if(StringUtils.isEmpty(resultJson)) {
				 result = findItemCatList(parentId);
				 String resultToCache=
				 objectMapper.writeValueAsString(result);
				 jedisCluster.set(key,resultToCache);
				 System.out.println("查询数据库，添加到缓存");
			}else {
				System.out.println("走了缓存-----************************");
				EasyUITree[] trees=
				objectMapper.readValue(resultJson, EasyUITree[].class);	
				result= Arrays.asList(trees);
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

}
