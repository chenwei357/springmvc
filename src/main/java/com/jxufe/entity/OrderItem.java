package com.jxufe.entity;

//订单项
public class OrderItem {

	private int itemId;
	private int count;
	private double subtotal;
	private int pid;
	private Product product;
	private int  oid;
	private Order order;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", count=" + count + ", subtotal=" + subtotal + ", product=" + product
				+ ", oid=" + oid + "]";
	}
	
}
