package com.jxufe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxufe.entity.RegisterForm;
import com.jxufe.entity.User;
import com.jxufe.service.UserService;
import com.jxufe.utils.MD5Utils;
import com.jxufe.utils.MailUtils;
import com.jxufe.utils.WebUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	* 方法名: register
	* 方法作用: 用户注册
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月16日 下午2:32:34   
	* @param @param request
	* @param @param response
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/registerPage")
	public String registerPage(HttpServletRequest request,HttpServletResponse response){
		
		return "register";
	}
	
	/**
	* 方法名: findByName
	* 方法作用: 通过用户名查询用户，用户验证用户名是否已存在
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月17日 上午9:56:54   
	* @param @param request
	* @param @param response
	* @param @return    
	* 返回值类型： int    
	* @throws
	*/
	@RequestMapping("/findByName")
	@ResponseBody
	public String findByName(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		return userService.findByName(username);
	}
	
	/**
	* 方法名: register
	* 方法作用: 用户注册，并发送激活邮件
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月18日 上午10:18:01   
	* @param @param request
	* @param @param response
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response){
		RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
		boolean b = form.validate(request);
		if(!b){
			request.setAttribute("form", form);
			return "forward:registerPage";
		}
		User user = new User();
		WebUtils.copyBean(user,form);
		
		user.setPassword(MD5Utils.md5(user.getPassword()));	//密码加密
		user.setState(0);	//0:代表用户未激活.  1:代表用户已经激活.
		//激活码
		String code = WebUtils.generateID() + WebUtils.generateID();
		
		user.setCode(code);
		userService.insertUser(user);
		MailUtils.sendMail(user.getEmail(), code);	//发送邮件	
		
		request.setAttribute("msg", "注册成功，请去邮箱激活!");
		return "msg";
	}
	
	/**
	* 方法名: active
	* 方法作用: 用户激活
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月18日 上午11:09:34   
	* @param @param request
	* @param @param code
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/active")
	public String active(HttpServletRequest request,String code){
		User existUser = userService.findByCode(code);
		if(existUser == null){
			request.setAttribute("msg", "激活失败:激活码错误!");
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.updateActive(existUser);
			request.setAttribute("msg", "激活成功:请去登录!");
		}
		return "msg";
	}
	/**
	* 方法名: loginPage
	* 方法作用: 登陆页面
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月18日 下午1:56:08   
	* @param @param request
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest request){
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user,String checkcode){
		//将User的密码加密后带到数据库去查询
		user.setPassword(MD5Utils.md5(user.getPassword()));
		User existUser = userService.findUser(user);
		if(existUser == null){	//登陆失败，用户名密码错误
			request.setAttribute("errors", "用户名或密码错误");
			return "forward:loginPage";
		}else{
			if(existUser.getState() == 0){
				//登陆失败，用户未激活
				request.setAttribute("errors", "用户未激活,请激活后再登陆");
				return "forward:loginPage";
			}else{
				if(!checkcode.equalsIgnoreCase((String) request.getSession().getAttribute("checkcode"))){
					request.setAttribute("errors", "验证码错误!");
					return "forward:loginPage";
				}else{
					//登陆成功
					//用户信息存入session
					request.getSession().setAttribute("User", existUser);
					//页面跳转
					return "redirect:../index";
				}
			}
		}
	}
	
	/**
	* 方法名: logout
	* 方法作用: 注销登陆
	* 创建人：Jxufe Chenwei
	* 创建时间：2016年8月18日 下午3:46:01   
	* @param @param request
	* @param @return    
	* 返回值类型： String    
	* @throws
	*/
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:../index";
	}
}
