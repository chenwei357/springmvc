package com.jxufe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {

	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		try {
			//1.创建要封装的bean
			T bean = beanClass.newInstance();
			
			//2.把数据整到bean中
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void copyBean(Object bean,Object bean2){
		
		ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if (value == null) {
					return null;
				}
				String str = (String) value;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		try {
			//将bean2复制到bean中
			BeanUtils.copyProperties(bean, bean2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//产生唯一的Id,用作激活码
	public static String generateID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
