package com.upem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upem.models.DeviceData;

@Repository
public interface DataRepository extends CrudRepository<DeviceData, Integer>{

	

	@Query(value = "SELECT * FROM device_data where id =?1", nativeQuery = true)
	DeviceData getbyId(Integer id);
	
	@Query(value = " SELECT * FROM device_data where antenne_id =?1 order by date desc limit 1 ;", nativeQuery = true)
	DeviceData getDataByAntenne(Integer id);
	
	@Query(value = " SELECT COUNT(*)  FROM device_data where antenne_id =?1 ;", nativeQuery = true)
	 int getallDataByAntenne(Integer id);
	
	@Query(value = "SELECT  * FROM device_data where datadevice_id=?1 order by date desc limit 10;", nativeQuery = true)
	List<DeviceData> getTempHum(Integer id);
	
	@Query(value = "SELECT * FROM device_data where datadevice_id=?1 group by EXTRACT(DAY FROM date) ORDER BY EXTRACT(DAY FROM date) desc ;", nativeQuery = true)
	List<DeviceData> getdataOrderByDay(Integer id);
	

}
