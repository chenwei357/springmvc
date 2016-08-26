package com.jxufe.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxufe.entity.Cart;
import com.jxufe.entity.CartItem;
import com.jxufe.entity.Order;
import com.jxufe.entity.OrderItem;
import com.jxufe.entity.Product;
import com.jxufe.entity.User;
import com.jxufe.service.OrderService;
import com.jxufe.service.ProductService;
import com.jxufe.utils.Page;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
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
	
	/**
	* 方法名: findMyOrder
	* 方法作用: 查询我的订单
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月26日 上午12:14:36   
	* @param @param request
	* @param @param pageNo
	* @param @param page
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("myOrder")
	public String findMyOrder(HttpServletRequest request,int pageNo,Page<Order> page){
		User user = (User) request.getSession().getAttribute("User");
		//根据用户的id进行查询
		//调用service
		page = orderService.findByUid(user.getUid(),pageNo);
		List<Order> list2 = page.getList();
		//将商品信息加到orderItem中
		for(int i = 0; i < list2.size(); i++ ){
			List<OrderItem> list1 = page.getList().get(i).getOrderItems();	
			for(int j = 0; j < list1.size(); j++ ){
				Product product = productService.findById(list1.get(j).getPid());
				list1.get(j).setProduct(product);
			}
		}
		
		request.setAttribute("list", page);
		return "orderList";
	}
	
	@RequestMapping("findOrder")
	public String findOrder(HttpServletRequest request,int oid){
		Order order = orderService.findByOid(oid);
		User user = (User) request.getSession().getAttribute("User");
		// 订单所属的用户
		order.setUser(user);

		// 设置订单中的订单项
		
		List<OrderItem> list = orderService.getOrderItems(oid);
		for(int i = 0 ; i < list.size() ; i++ ){
			Product product = productService.findById(list.get(i).getPid());
			list.get(i).setProduct(product);
		}
		order.setOrderItems(list);
		
		request.setAttribute("orders", order);
		return "order";
	}
	
}
