package com.jxufe.entity;

import java.io.Serializable;

//二级分类
public class CategorySecond implements Serializable{

	private int csid;
	private String csname;
	//所属一级分类id
	private int cid;

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCsid() {
		return csid;
	}
	public void setCsid(int csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public CategorySecond() {
		super();
	}
	
}
