package test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.auth.service.IUserService;

public class UserDaoTest{
	
	private IUserService userService;
	
	@Before
	public void beforeCase(){
		ApplicationContext app=new FileSystemXmlApplicationContext("src/main/resources/META-INF/spring/root-context.xml");
		userService=(IUserService) app.getBean("iUserService");
	}
	
	@Test
	public void insertUser(){
		User entity=new User();
		entity.setEmail("zhangjiaolong20068@163.com");
		entity.setDel(false);
		entity.setCreateTime(new Date());
		entity.setGender(1);
		entity.setLock(false);
		entity.setLoginName("zhangjiaolong");
		entity.setName("张蛟龙");
		String password=null;
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		entity.setPassword(md5.encodePassword("123456", "zhangjiaolong"));
		userService.insertByEntity(entity);
	}
}
