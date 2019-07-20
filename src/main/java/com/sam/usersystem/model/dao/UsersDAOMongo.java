package com.sam.usersystem.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.sam.usersystem.model.UsersBean;

@Repository
public class UsersDAOMongo implements UsersDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public UsersBean insert(UsersBean bean) {
		try {
	        bean.setNo(mongoTemplate.findAll(UsersBean.class).size() + 1);
			mongoTemplate.insert(bean);
		} catch (DuplicateKeyException e) {
//			e.printStackTrace();
			return null;
		}
		return bean;
	}

	@Override
	public UsersBean selectById(String userID) {
		
		UsersBean bean = null;
		
		try {
			bean = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("userID").is(userID)), UsersBean.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bean;
	}

	@Override
	public UsersBean selectByName(String userName) {
		UsersBean bean = null;
		
		try {
			bean = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("userName").is(userName)), UsersBean.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bean;
	}

	@Override
	public List<String> selectLikeName(String userName) {
		List<UsersBean> list = null;
		
		String userNameRegex = "^" + userName + ".*$";
		
		try {
			list = mongoTemplate.find(new Query().addCriteria(Criteria.where("userName").regex(userNameRegex)), UsersBean.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		List<String> result = new ArrayList<>();
		
		for(UsersBean bean : list) {
			result.add(bean.getUserName());
		}
		
		return result;
	}

}
