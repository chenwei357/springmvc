package com.jxufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxufe.dao.UserMapper;
import com.jxufe.entity.User;
import com.jxufe.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAll() {
		return userMapper.findAll();
	}

	public String findByName(String username) {
		return userMapper.findByName(username);
	}

	public void insertUser(User user){
		userMapper.insertUser(user);
	}
	
	public User findByCode(String code){
		return userMapper.findByCode(code);
	}

	public void updateActive(User user) {
		userMapper.updateActive(user);
	}
	
	public User findUser(User user){
		return userMapper.findUser(user);
	}
}
