package com.jxufe.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxufe.entity.Cart;
import com.jxufe.entity.CartItem;
import com.jxufe.entity.Order;
import com.jxufe.entity.OrderItem;
import com.jxufe.entity.User;
import com.jxufe.service.OrderService;
import com.jxufe.utils.Page;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		Order order = new Order();
		//1.保存数据到数据库
		//订单数据补全
		order.setOrderTime(new Date());
		order.setState(1);		//1.未付款	2.已经付款,但没有发货		3.已经发货,但没有确认收货		4.交易完成
		//总计数据：从购物车中获取
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			request.setAttribute("msg", "亲!您还没有购物!请先去购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		
		User user = (User) request.getSession().getAttribute("User");
		if(user == null){
			request.setAttribute("msg", "亲!您还没有登陆!请先去登陆!");
			return "msg";
		}
		//订单所属的用户
		order.setUser(user);
		
		//设置订单中的订单项
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem orderItem = new OrderItem();
			
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOid(order.getOid());
			
			order.getOrderItems().add(orderItem);
		}
		
		orderService.save(order);
		//2.将订单对象显示到页面上
		request.setAttribute("orders", order);
		return "order";
	}
	
	@RequestMapping("myOrder")
	public String findMyOrder(HttpServletRequest request,int pageNo,Page<Order> page){
		User user = (User) request.getSession().getAttribute("User");
		//根据用户的id进行查询
		//调用service
		page = orderService.findByUid(user.getUid(),pageNo);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		
		request.setAttribute("list", page);
		return "orderList";
	}
	
}
