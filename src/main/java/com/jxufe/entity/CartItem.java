package com.jxufe.entity;

import java.io.Serializable;

//购物项
public class CartItem implements Serializable{

	private Product product;		//商品信息
	private int count;				//商品数量
	private double subtotal;		//小计,自动计算
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return count * product.getShopPrice();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
}
