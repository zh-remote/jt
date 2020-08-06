package com.jt.search.pojo;

import org.apache.solr.client.solrj.beans.Field;

import com.jt.common.po.BasePojo;

//对应solr中的数据
public class Item extends BasePojo {

	@Field("id")//对应solr中的id
	private Long id;
	@Field("title")
	private String title;
	
	@Field("sellPoint")
	private String sellPoint;
	
	@Field("image")
	private String images;
	
	@Field("price")
	private Long price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public String[] getImages() {
		return images.split(",");
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	
	
	
}
