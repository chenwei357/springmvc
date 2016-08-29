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
import com.jxufe.utils.PaymentUtil;

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
			orderItem.setOrder(order);
			
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
	
	/**
	* 方法名: findOrder
	* 方法作用: 通过oid查找订单详情
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月27日 上午11:42:22   
	* @param @param request
	* @param @param oid
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
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
	
	/**
	* 方法名: payOrder
	* 方法作用: 更新orders表中的信息,并付款
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月27日 下午3:21:52   
	* @param @param request
	* @param @param oid
	* @param @param pd_FrpId
	* @param @param username
	* @param @param phone
	* @param @param addr
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/payOrder")
	public String payOrder(HttpServletRequest request,int oid,String pd_FrpId // 支付通道编码:
			,String username,String phone,String addr){
		//1.修改订单
		String oid2 = String.valueOf(oid);
		orderService.update(oid2,username,phone,addr);
		//2.付款
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = oid + "";// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/springmvc/order/callback"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向:向易宝出发:
		return "redirect:" + sb.toString();
		
	}
	
	/**
	* 方法名: callBack
	* 方法作用: 支付成功后跳转到全局消息显示页面
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月27日 下午3:52:21   
	* @param @param request
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/callback")
	public String callBack(HttpServletRequest request){
		// 修改订单的状态:
		orderService.updateState(request.getParameter("r6_Order"));
		request.setAttribute("msg", "支付成功!订单编号为: "+request.getParameter("r6_Order") +",付款金额为: "+request.getParameter("r3_Aamt")+"");
		System.out.println("支付成功!订单编号为: "+request.getParameter("r6_Order") +",付款金额为: "+request.getParameter("r3_Amt")+"");
		return "msg";
	}
	
}
