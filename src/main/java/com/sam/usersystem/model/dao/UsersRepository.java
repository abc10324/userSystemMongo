package com.sam.usersystem.model.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	default UsersBean insertWithAutoGenNo(UsersBean bean) {
		List<UsersBean> list = findAll(Sort.by(Sort.Direction.DESC, "no"));

		bean.setNo(list.size() == 0 ? 1 : list.get(0).getNo() + 1);
		
		try {
			insert(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return bean;
	}
	
}
