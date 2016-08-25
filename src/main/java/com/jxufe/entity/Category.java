package com.jxufe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//一级分类
public class Category implements Serializable{
	private int cid;
	private String cname;
	//一级分类中存放二级分类的集合:
	private List<CategorySecond> categorySeconds = new ArrayList<CategorySecond>();
	
	public List<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(List<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public Category() {
		super();
	}
	public Category(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", categorySeconds=" + categorySeconds + "]";
	}

}
