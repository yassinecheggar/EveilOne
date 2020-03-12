package com.upem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upem.models.Antenne;
import com.upem.models.User;

@Repository
public interface AntenneRepository extends CrudRepository<Antenne, Integer>{

	
	@Query(value = "SELECT * FROM antenne where id =?1", nativeQuery = true)
	Antenne getbyId(Integer id);
	 

}
