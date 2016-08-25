package com.jxufe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.jxufe.entity.Category;
import com.jxufe.entity.Product;
import com.jxufe.service.CategoryService;
import com.jxufe.service.ProductService;

@Controller
public class IndexController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		//查询一级分类
		List<Category> clist = categoryService.findAll();	
		clist.get(0).getCategorySeconds();
		request.getSession().setAttribute("clist", clist);
		//查询热门商品
		List<Product> plisst = productService.findHot();
		request.getSession().setAttribute("plist", plisst);
		//查询最新商品
		List<Product> nlist = productService.findNew();
		request.getSession().setAttribute("nlist", nlist);
		return "index";
	}	
}
