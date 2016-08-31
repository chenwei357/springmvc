package springmvc;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jxufe.dao.UserMapper;
import com.jxufe.entity.User;
import com.jxufe.utils.MD5Utils;

public class testUser {

	@Test
	public void findAll(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-config.xml");
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		List<User> list = userMapper.findAll();
		for(User user : list){
			System.out.println(user);
		}
	}
	@Test
	public void testMD5(){
		String message = "admin";
		System.out.println(MD5Utils.md5(message));
	}
	
}
