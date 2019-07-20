package com.sam.usersystem.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sam.usersystem.model.UsersBean;

public class UsersDAOMongoTest {
	private ApplicationContext context;
	private UsersDAO usersDAO;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(com.sam.usersystem.config.SpringJavaConfig.class);
		
		usersDAO = (UsersDAO) context.getBean("usersDAOMongo");
		
	}
	
	@Test
	public void insert() {
		
		UsersBean bean = new UsersBean();
		String userID = "abc10327";
		String userName = "Sam3";
		String pwd = "abc9814016";
		
		bean.setUserID(userID);
		bean.setPwd(pwd);
		bean.setUserName(userName);
		
		UsersBean result = null;
		
		result = usersDAO.insert(bean);
		
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("Insert fail");
		}
		
	}
	
	
	@Test
	public void selectById() {
		String userID = "abc10324";
//		String userID = "abc9814016";
		
		UsersBean result = null;
		result = usersDAO.selectById(userID);
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("No Result");
		}
	}
	
	@Test
	public void selectByName() {
		String userName = "Sam";
//		String userName = "Jenny";
		
		UsersBean result = null;
		result = usersDAO.selectByName(userName);
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("No Result");
		}
	}
	
	@Test
	public void selectLikeName() {
		String userName = "Sam";
//		String userName = "Jenny";
		
		List<String> result = null;
		result = usersDAO.selectLikeName(userName);
		
		if(result.size() != 0) {
			for(String resultUserName : result) {
				System.out.println(resultUserName);
			}
		} else {
			System.out.println("No Result");
		}
	}
	
	
	@After
	public void destroy() {
		((ConfigurableApplicationContext) context).close();
	}
	
}
