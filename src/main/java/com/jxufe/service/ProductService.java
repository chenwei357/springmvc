package com.jxufe.service;

import java.util.List;
import com.jxufe.entity.Product;
import com.jxufe.utils.Page;

public interface ProductService {

	public List<Product> findHot();
	public List<Product> findNew();
	public Product findById(int id);
	public Page<Product> findByPageNoAndCid(int cid,int pageNo,int count);
	public Page<Product> findPageByCsid(int csid, int pageNo, int count);
}
