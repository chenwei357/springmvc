package com.jxufe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//订单
public class Order implements Serializable{

	private int oid;
	private double total;
	private Date orderTime;
	private int state;
	private String name;
	private String phone;
	private String addr;
	private int uid;
	//订单所属用户
	private User user;
	//订单中所属的多个订单项
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", total=" + total + ", orderTime=" + orderTime + ", state=" + state + ", name="
				+ name + ", phone=" + phone + ", addr=" + addr + ", uid=" + uid + ", user=" + user + ", orderItems="
				+ orderItems + "]";
	}
	
}
