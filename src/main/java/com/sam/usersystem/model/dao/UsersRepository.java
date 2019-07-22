package com.sam.usersystem.model.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sam.usersystem.model.UsersBean;

@Repository
public interface UsersRepository extends MongoRepository<UsersBean, String> {
	
	@Query("{'userID' : ?0 }")
	public UsersBean selectById(String userID);
	
	@Query("{'userName' : {$regex : '^?0$' , $options: 'i'} }")
	public UsersBean selectByName(String userName);
	
	@Query("{'userName' : {$regex : '^?0.*$' , $options: 'i'} }")
	public List<UsersBean> selectLikeName(String userName);
	
	@Query("{'userName' : {$regex : '^?0.*$' , $options: 'i'} }")
	public List<UsersBean> selectLikeName(String userName,Pageable pageable);
	
	public UsersBean findFirstByOrderByNoDesc();
	
	/* this default method syntax only for java 8 above
	 * java 7 is not allowed
	*/ 
	default UsersBean insertWithAutoGenNo(UsersBean bean) {
		UsersBean result = findFirstByOrderByNoDesc();

		bean.setNo(result == null ? 1 : result.getNo() + 1);
		
		try {
			insert(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return bean;
	}
	
}
