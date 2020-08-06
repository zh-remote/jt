package com.jt.search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.search.pojo.Item;

public class SearchServiceImpl implements SearchService {

	@Autowired
	HttpSolrServer httpSolrServer ;
	@Override
	public List<Item> findItemByKey(String key) {
		List<Item> items=null;
		try {
			//查询对象 
			SolrQuery solrQuery =new SolrQuery();
			solrQuery.setStart(0);
			solrQuery.setRows(20);
			QueryResponse queryResponse=httpSolrServer.query(solrQuery);
			items = queryResponse.getBeans(Item.class);//根据注解注入值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

}
