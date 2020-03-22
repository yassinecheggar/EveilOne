package com.upem.repository;

import java.sql.Date;
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
	
	@Query(value = "SELECT * FROM localisation where deviceloca_id =?1 order by date desc limit 1", nativeQuery = true)
	localisation getcurentlocationwithId(Integer id);
	
	@Query(value = "SELECT * FROM localisation where deviceloca_id =?1 group by EXTRACT(DAY FROM date) ORDER BY EXTRACT(DAY FROM date) desc ;", nativeQuery = true)
	List<localisation> getDaylocation(Integer id);
	 

	@Query(value = "SELECT * FROM localisation   WHERE DATE(date) =?2 and  deviceloca_id=?1", nativeQuery = true)
	List<localisation> getlocaBydate(Integer id, String date);
}
