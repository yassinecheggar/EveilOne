package com.upem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upem.models.Antenne;
import com.upem.models.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	
	@Query(value = "SELECT * FROM user where mail=?1 and mdp =?2", nativeQuery = true)
	User login(String mail , String mdp);
	
	@Query(value = "SELECT * FROM user where id =?1", nativeQuery = true)
	User getbyId(Integer id);

}
