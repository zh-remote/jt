package com.jt.vo;

import java.util.List;

/**
 * 封装类目信息 
 * @author asus
 *
 */
public class EasyUITree {

	private Long id;
	private String text;
	private String state;
	List<EasyUITree> children;
	
	public List<EasyUITree> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
