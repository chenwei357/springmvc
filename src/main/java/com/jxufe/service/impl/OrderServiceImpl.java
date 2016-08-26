package com.jxufe.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxufe.dao.OrderMapper;
import com.jxufe.entity.Order;
import com.jxufe.entity.OrderItem;
import com.jxufe.service.OrderService;
import com.jxufe.utils.Page;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	/**
	* 方法名: save
	* 方法作用: 添加订单
	* 创建人:Chenwei
	* 创建时间：2016年8月25日 上午9:38:35   
	* @param order
	* @see com.jxufe.service.OrderService#save(com.jxufe.entity.Order) 
	*/
	public void save(Order order){
		orderMapper.addOrder(order);
		for(OrderItem orderItem : order.getOrderItems()){
			orderMapper.addOrderItem(orderItem);
		}
	}

	/**
	* 方法名: findByUid
	* 方法作用: 通过用户id查询用户订单
	* 创建人:Chenwei
	* 创建时间：2016年8月25日 下午1:50:27   
	* @param uid
	* @param pageNo
	* @return
	* @see com.jxufe.service.OrderService#findByUid(int, int) 
	*/
	public Page<Order> findByUid(int uid, int pageNo) {
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		int count = 5;
		page.setCount(count);
		int totalCount = 0;
		totalCount = orderMapper.findCountByUid(uid);
		page.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % count == 0){
			totalPage = totalCount / count;
		}else{
			totalPage = totalCount / count + 1;
		}
		page.setTotalPage(totalPage);
		//设置每页数据显示的集合
		int begin = (pageNo - 1) * count;
		List<Order> list = orderMapper.findPageByUid(uid,begin,count);
		
		page.setList(list);
		
		return page;
	}

	/**
	* 方法名: findByOid
	* 方法作用: 通过oid查找订单order
	* 创建人:Chenwei
	* 创建时间：2016年8月26日 上午12:21:53   
	* @param oid
	* @return
	* @see com.jxufe.service.OrderService#findByOid(int) 
	*/
	public Order findByOid(int oid) {

		return orderMapper.findByOid(oid);
	}
	
	public List<OrderItem> getOrderItems(int oid){
		
		return orderMapper.getOrderItems(oid);
	}
	
}
