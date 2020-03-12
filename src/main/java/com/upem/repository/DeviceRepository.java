package com.upem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upem.models.Antenne;
import com.upem.models.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {

	
	@Query(value = "SELECT * FROM device where idf =?1 and cle =?2", nativeQuery = true)
	Device getbyId(String idf, String key);
	
	@Query(value = "SELECT * FROM device where idf=?1", nativeQuery = true)
	Device getbyIdMAc(String idf);
	 
}
