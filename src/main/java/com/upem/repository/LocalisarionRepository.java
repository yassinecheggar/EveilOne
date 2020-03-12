package com.upem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.upem.models.Antenne;
import com.upem.models.localisation;

public interface LocalisarionRepository extends CrudRepository<localisation, Integer>{

	
	@Query(value = "SELECT * FROM localisation where id =?1", nativeQuery = true)
	localisation getbyId(Integer id);

	@Query(value = "SELECT * FROM localisation where deviceloca_id =?1", nativeQuery = true)
	List<localisation> getbydevice(Integer id);
	 

}
