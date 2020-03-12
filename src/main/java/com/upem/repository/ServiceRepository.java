package com.upem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upem.models.Service;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

	
	@Query(value = "SELECT * FROM service where id =?1", nativeQuery = true)
	Service getbyId(Integer id);
}
