package com.jxufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxufe.dao.ProductMapper;
import com.jxufe.entity.Product;
import com.jxufe.service.ProductService;
import com.jxufe.utils.Page;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	/**
	* 方法名: findHot
	* 方法作用: 查询热门商品,只显示前十条(按日期排序)。
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月22日 下午1:51:28   
	* @param @return    
	* 返回值类型： List<Product>    
	* @throws
	*/
	public List<Product> findHot(){
		return productMapper.findHot();
	}
	
	/**
	* 方法名: findNew
	* 方法作用: 查询最新商品
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月22日 下午2:32:17   
	* @param @return    
	* 返回值类型： List<Product>    
	* @throws
	*/
	public List<Product> findNew(){
		return productMapper.findNew();
	}

	/**
	* 方法名: findById
	* 方法作用: 通过id查找商品详细信息
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月22日 下午2:56:18   
	* @param @param id
	* @param @return    
	* 返回值类型： Product    
	* @throws
	*/
	public Product findById(int id){
		return productMapper.findById(id);
	}
	
	/**
	* 方法名: findByPageNoAndCid
	* 方法作用: 分页
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月23日 下午3:26:40   
	* @param @param cid
	* @param @param pageNo
	* @param @return    
	* 返回值类型： Page<Product>    
	* @throws
	*/
	public Page<Product> findByPageNoAndCid(int cid,int pageNo,int count){
		Page<Product> page = new Page<Product>();
		page.setPageNo(pageNo);		//设置当前页数
		int totalCount = 0; 		//设置总记录数
		totalCount = productMapper.findCountByCid(cid);
		page.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		count = page.getCount();
		if (totalCount % count == 0) {
			totalPage = totalCount / count;
		} else {
			totalPage = totalCount / count + 1;
		}
		page.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始：
		int begin = (pageNo - 1) *  count;
		List<Product> list = productMapper.findByPageAndCid(cid,begin,count);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}
	
	/**
	* 方法名: findPageByCsid
	* 方法作用: 根据二级分类查询商品信息
	* 创建人:Chenwei
	* 创建时间：2016年8月24日 下午1:57:47   
	* @param csid
	* @param pageNo
	* @return
	* @see com.jxufe.service.ProductService#findPageByCsid(int, int) 
	*/
	public Page<Product> findPageByCsid(int csid, int pageNo, int count){
		Page<Product> page = new Page<Product>();
		page.setPageNo(pageNo);		//设置当前页数
		int totalCount = 0; 		//设置总记录数
		totalCount = productMapper.findCountByCsid(csid);
		page.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		count = page.getCount();
		count = page.getCount();
		if (totalCount % count == 0) {
			totalPage = totalCount / count;
		} else {
			totalPage = totalCount / count + 1;
		}
		page.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始： 
		int begin = (pageNo - 1) *  count;
		List<Product> list = productMapper.findByPageAndCsid(csid,begin,count);
		if(list != null && list.size() > 0){
			page.setList(list);
			return page;
		}
		return null;
	}
	
}
