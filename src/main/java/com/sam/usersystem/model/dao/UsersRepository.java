package com.sam.usersystem.model.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sam.usersystem.model.UsersBean;

@Repository
public interface UsersRepository extends MongoRepository<UsersBean, String> {
	
	@Query("{'userID' : ?0 }")
	public UsersBean selectById(String userID);
	
	@Query("{'userName' : {$regex : '^?0$' , $options: 'i'} }")
	public UsersBean selectByName(String userName);
	
	@Query("{'userName' : {$regex : '^?0.*$' , $options: 'i'} }")
	public List<UsersBean> selectLikeName(@Param("userName") String userName);
	
}
