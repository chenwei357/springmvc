package com.jxufe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxufe.entity.Cart;
import com.jxufe.entity.CartItem;
import com.jxufe.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductService productService;
	
	
	/**
	* 方法名: add
	* 方法作用: 添加购物车
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月24日 下午4:55:48   
	* @param @param request
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/add")
	public String add(HttpServletRequest request,int pid,int count){
		//封装一个cartItem对象
		CartItem cartItem = new CartItem();
		//设置数量
		cartItem.setCount(count);
		//设置商品
		cartItem.setProduct(productService.findById(pid));
		//将购物项添加到购物车
		//购物车应该存在sesseion中
		Cart cart = getCart(request);
		cart.addCart(cartItem);
		return "cart";
	}

	/**
	* 方法名: clearCart
	* 方法作用: 清空购物车
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月24日 下午9:17:01   
	* @param @param request
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request){
		//获取购物车对象
		Cart cart = getCart(request);
		//调用购物车中的清空方法
		cart.clearCart();
		return "cart";
	}
	
	/**
	* 方法名: delete
	* 方法作用: 购物车中移除购物项
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月24日 下午9:23:33   
	* @param @param request
	* @param @param pid
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,int pid){
		//获取购物车对象
		Cart cart = getCart(request);
		//调用购物车中的移除方法
		cart.removeCart(pid);
		return "cart";
	}
	
	@RequestMapping("/myCart")
	public String myCart(HttpServletRequest request){
		return "cart";
	}
	
	/**
	* 方法名: getCart
	* 方法作用:获得购物车
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月24日 下午5:13:15   
	* @param @param request
	* @param @return    
	* 返回值类型： Cart    
	* @throws
	*/
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
}
