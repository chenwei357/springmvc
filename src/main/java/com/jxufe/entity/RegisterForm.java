package com.jxufe.entity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//注册表单
public class RegisterForm {

	private String username;
	private String password;
	private String password2;
	private String name;
	private String phone;
	private String addr;
	private String checkcode;
	private String email;
	
	private Map errors = new HashMap();
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public Map getErrors() {
		return errors;
	}
	public void setErrors(Map errors) {
		this.errors = errors;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*
 	 *private String username;  用户名不能为空.
	 *private String password2; 两次密码要一致
	 *private String checkcode; 验证码不能为空，并且要一致
	 *private String email;     可以为空，不为空要是一个合法的邮箱
	 */
	public boolean validate(HttpServletRequest request){
		String username = this.username;
		boolean isOk = true;
		if(this.username == null || this.username.trim().equals("")){
			isOk = false;
			errors.put("username", "用户名不能为空!");
		}
		
		if(this.password == null || this.password.trim().equals("")){
			isOk = false;
			errors.put("password", "密码不能为空!");
			}
		
		if(this.password2 == null || this.password2.trim().equals("")){
			isOk = false;
			errors.put("password2", "确认密码不能为空!");
		}else {
			if(!this.password.equals(this.password2)){
				isOk = false;
				errors.put("password2", "两次密码不一致!");
			}
		}
		
		if (this.checkcode == null || this.checkcode.trim().equals("")) {
			isOk = false;
			errors.put("checkcode", "请输入验证码!");
		}else {
			String s_checkcode = (String) request.getSession().getAttribute("checkcode");
			if(!s_checkcode.equalsIgnoreCase(this.checkcode)){
				isOk = false;
				errors.put("checkcode", "验证码错误，请重新输入");
			}
		}
		// private String email; 可以为空，不为空要是一个合法的邮箱
		// flx_itcast@sina.com.cn
		if (this.email != null) {
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOk = false;
				errors.put("email", "邮箱不是一个合法邮箱！！");
			}
		}
		return isOk;
	}
	
}
