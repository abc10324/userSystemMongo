package com.sam.usersystem.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.sam.usersystem.model.UsersBean;

public class UsersRepositoryTest {
	private ApplicationContext context;
	private UsersRepository usersRepository;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(com.sam.usersystem.config.SpringJavaConfig.class);
		
		usersRepository = (UsersRepository) context.getBean("usersRepository");
		
	}
	
	@Test
	public void insert() {
		
		UsersBean bean = new UsersBean();
		String userID = "abc10325";
		String userName = "sam1";
		String pwd = "abc9814016";
		
		bean.setNo(Integer.valueOf((int)(usersRepository.count() + 1)));
		bean.setUserID(userID);
		bean.setPwd(pwd);
		bean.setUserName(userName);
		
		UsersBean result = null;
		
		result = usersRepository.insert(bean);
		
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("Insert fail");
		}
		
	}
	
	@Test
	public void insertWithAutoGenNo() {
		
		UsersBean bean = new UsersBean();
		String userID = "abc10327";
		String userName = "Sam6";
		String pwd = "abc9814016";
		
		bean.setNo(Integer.valueOf((int)(usersRepository.count() + 1)));
		bean.setUserID(userID);
		bean.setPwd(pwd);
		bean.setUserName(userName);
		
		UsersBean result = null;
		
		result = usersRepository.insertWithAutoGenNo(bean);
		
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("Insert fail");
		}
		
	}
	
	
	@Test
	public void selectById() {
		String userID = "abc10382";
//		String userID = "abc9814016";
		
		UsersBean result = null;
		result = usersRepository.selectById(userID);
		
		if(result != null) {
			System.out.println(result);
		} else {
			System.out.println("No Result");
		}
	}
	
	@Test
	public void selectByName() {
//		String userName = "Sam";
		String userName = "Jade";
		
		UsersBean result = null;
		result = usersRepository.selectByName(userName);
		
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
		
		List<UsersBean> beanList = usersRepository.selectLikeName(userName);
		List<String> result = new ArrayList<>();
		
		for(UsersBean bean : beanList) {
			result.add(bean.getUserName());
		}
		
		if(result.size() != 0) {
			for(String resultUserName : result) {
				System.out.println(resultUserName);
			}
		} else {
			System.out.println("No Result");
		}
	}
	
	@Test
	public void selectLikeNameWithPagination() {
		String userName = "Sam";
//		String userName = "Jenny";
		
		List<UsersBean> beanList = usersRepository.selectLikeName(userName,PageRequest.of(0, 2, Sort.Direction.ASC, "no"));
		List<String> result = new ArrayList<>();
		
		for(UsersBean bean : beanList) {
			result.add(bean.getUserName());
		}
		
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
