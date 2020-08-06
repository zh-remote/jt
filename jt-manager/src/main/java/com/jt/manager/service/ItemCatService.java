package com.jt.manager.service;

import java.util.List;

import com.jt.vo.EasyUITree;



public interface ItemCatService {

	List<EasyUITree> findItemCatList(Long parentId);

	List<EasyUITree> findItemCatCache(Long parentId);

	
}
