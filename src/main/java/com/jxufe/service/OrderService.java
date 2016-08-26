package com.jxufe.service;


import java.util.List;

import com.jxufe.entity.Order;
import com.jxufe.entity.OrderItem;
import com.jxufe.utils.Page;

public interface OrderService {

	public void save(Order order);

	public Page<Order> findByUid(int uid, int pageNo);

	public Order findByOid(int oid);

	public List<OrderItem> getOrderItems(int oid);
	
}
