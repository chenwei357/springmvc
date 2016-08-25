package com.jxufe.entity;

import java.io.Serializable;
import java.util.Date;

//商品
public class Product implements Serializable{

	private int pid;
	private String pname;
	private Double marketPrice;
	private Double shopPrice;
	private String image;
	private String pdesc;
	private int isHot;
	private Date pdate;
	private int csid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pDate) {
		this.pdate = pDate;
	}
	public int getCsid() {
		return csid;
	}
	public void setCsid(int csid) {
		this.csid = csid;
	}
	public Product(int pid, String pname, Double marketPrice, Double shopPrice, String image, String pdesc, int isHot,
			Date pDate, int csid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.marketPrice = marketPrice;
		this.shopPrice = shopPrice;
		this.image = image;
		this.pdesc = pdesc;
		this.isHot = isHot;
		this.pdate = pDate;
		this.csid = csid;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", marketPrice=" + marketPrice + ", shopPrice=" + shopPrice
				+ ", image=" + image + ", pdesc=" + pdesc + ", isHot=" + isHot + ", pdate=" + pdate + ", csid=" + csid
				+ "]";
	}
	
}
