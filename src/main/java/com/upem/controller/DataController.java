package com.upem.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upem.models.Antenne;
import com.upem.models.DeviceData;
import com.upem.models.Service;
import com.upem.models.localisation;
import com.upem.repository.DataRepository;
import com.upem.repository.LocalisarionRepository;


@RestController
@RequestMapping("/api/data")
public class DataController {


	@Autowired
	DataRepository dataRepository;
	
	@Autowired
	LocalisarionRepository locRepo;
	
	@GetMapping("/getlocalisations/{id}")// get list of localization by device id
	public List<localisation> getlocalisations(@PathVariable Integer id) {
		
		return locRepo.getbydevice(id);
	}
	
	@GetMapping("/getcurrent/{id}") //get the last device localization 
	public localisation getcurrent(@PathVariable Integer id) {
		
		return locRepo.getcurentlocationwithId(id);
	}
	
	
	@GetMapping("/getTemp/{id}") // not used (will  be used soon ncha2la) 
	public List<DeviceData> getTemp(@PathVariable Integer id) {
		
		return dataRepository.getTempHum(id);
	}
	
	@GetMapping("/getDataOfDay/{id}")//get data grouped by day for more info  check  the repository 
	public List<DeviceData> getDataByDay(@PathVariable Integer id) {
		
		return dataRepository.getdataOrderByDay(id);
	}
	
	
	@GetMapping("/getlocaByDay/{id}") // get localization grouped by day
	public List<localisation> getlocaByDay(@PathVariable Integer id) {
		
		return locRepo.getDaylocation(id);
	}
	
	
	@GetMapping("/data") // get all  data 
	public List<DeviceData> getdata() { 
		
		return  (List<DeviceData>) dataRepository.findAll();
	}
	
	
	@PostMapping("/getlocaBydate/{id}")// add data to  data base with  device id 
	public List<localisation> getlocaBydate(@PathVariable Integer id ,@RequestBody DeviceData date) throws ParseException {
		
		
		java.sql.Date sqldat= java.sql.Date.valueOf(date.getHum());
		System.out.println(sqldat.toString());
		
		return locRepo.getlocaBydate(id, sqldat.toString());
	}
	
	
	
}
