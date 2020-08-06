package com.jt.search.service;

import java.util.List;

import com.jt.search.pojo.Item;

public interface SearchService {
	
	public List<Item> findItemByKey(String key);
}
