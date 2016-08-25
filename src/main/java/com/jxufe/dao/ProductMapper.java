package com.jxufe.dao;

import java.util.List;

import com.jxufe.entity.Product;

public interface ProductMapper {

	public List<Product> findHot();
	public List<Product> findNew();
	public Product findById(int id);
	public int findCountByCid(int cid);
	public List<Product> findByPageAndCid(int cid, int begin, int count);
	public int findCountByCsid(int csid);
	public List<Product> findByPageAndCsid(int csid, int begin, int count);
	
}
