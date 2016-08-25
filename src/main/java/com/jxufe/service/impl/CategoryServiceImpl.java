package com.jxufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxufe.dao.CategoryMapper;
import com.jxufe.entity.Category;
import com.jxufe.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	* 方法名: findAll
	* 方法作用: TODO
	* 创建人:Chenwei
	* 创建时间：2016年8月22日 上午9:45:12   
	* @return
	*/
	@Override
	public List<Category> findAll(){
		return categoryMapper.findAll();
	}
	
	
	
}
