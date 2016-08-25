package com.jxufe.dao;

import java.util.List;

import com.jxufe.entity.Order;
import com.jxufe.entity.OrderItem;

public interface OrderMapper {

	public void addOrder(Order order);
	public void addOrderItem(OrderItem orderItem);
	public int findCountByUid(int uid);
	public List<Order> findPageByUid(int uid, int begin, int count);
}
