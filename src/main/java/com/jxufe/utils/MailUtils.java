package com.jxufe.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

//邮件发送类
public class MailUtils {

	public static void sendMail(String to, String code) {
		// 1.获取连接对象
		Properties props = new Properties();
		props.setProperty("mail.debug", "true");
		 // 发送服务器需要身份验证 
		props.setProperty("mail.smtp.auth", "true");
		 // 设置邮件服务器主机名 
		//163邮箱：props.setProperty("mail.host", "smtp.163.com");
		//QQ
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp");  
        
        
        //  使用QQ邮箱发送邮件时
        MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        sf.setTrustAllHosts(true);  
        props.put("mail.smtp.ssl.enable", "true");  
        props.put("mail.smtp.ssl.socketFactory", sf);
         
        
        // 设置环境信息 
        Session session = Session.getInstance(props);
		/*163邮箱：
		 * Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("18702604854@163.com", "cw19950820");
			}
		});*/
		
		// 2.创建连接对象
		Message message = new MimeMessage(session);
		
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("920791352@qq.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC 密送BCC
			// 设置标题
			message.setSubject("来自购物商城官方激活邮件");
			// 设置邮件正文:
			message.setContent(
					"<h1>购物商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://119.29.107.160/springmvc/user/active?code="
							+ code + "'>http://119.29.107.160/springmvc/user/active?code=" + code + "</a></h3>",
					"text/html;charset=UTF-8");
			// 3.发送邮件:920791352@qq.com
			//163邮箱：	Transport.send(message);
			
			
			 //使用QQ邮箱发送邮件时
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", 465,"920791352@qq.com","opreqoddxhvfbdgh");
			transport.sendMessage(message, new Address[] {new InternetAddress(to)}); 
			 
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*	public static void main(String[] args) {
		sendMail("18702604854@163.com", "welcome");
	}*/
}
