package com.jxufe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxufe.entity.Product;
import com.jxufe.service.ProductService;
import com.jxufe.utils.Page;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	* 方法名: product
	* 方法作用: 商品详细页面
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月22日 下午4:24:14   
	* @param @param request
	* @param @param pid
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/detail")
	public String product(HttpServletRequest request,String pid){
		Product product = productService.findById(Integer.parseInt(pid));
		request.setAttribute("detail", product);
		return "product";
	}
	
	/**
	* 方法名: list
	* 方法作用: 商品列表分页
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月22日 下午4:24:33   
	* @param @param request
	* @param @param cid
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("list")
	public String list(HttpServletRequest request,int cid,int pageNo,Page<Product> page){
		page = productService.findByPageNoAndCid(cid, pageNo,page.getCount());
		request.setAttribute("list", page);
		request.setAttribute("id", cid);
		return "productList";
	}
	
	/**
	* 方法名: listed
	* 方法作用: 根据二级分类查询商品信息
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月24日 下午1:58:47   
	* @param @param request
	* @param @param csid
	* @param @param pageNo
	* @param @param page
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/listed")
	public String listed(HttpServletRequest request,int csid,int pageNo,Page<Product> page){
		page = productService.findPageByCsid(csid,pageNo,page.getCount());
		request.setAttribute("list", page);
		request.setAttribute("csid", csid);
		return "productList";
	}
	
}
