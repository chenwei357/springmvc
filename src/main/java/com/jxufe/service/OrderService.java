package com.jxufe.service;

import com.jxufe.entity.Order;
import com.jxufe.utils.Page;

public interface OrderService {

	public void save(Order order);

	public Page<Order> findByUid(int uid, int pageNo);

	
}
