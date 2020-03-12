package com.upem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/getloca/{id}")
	public List<localisation> getallServices(@PathVariable Integer id) {
		
		return locRepo.getbydevice(id);
	}
	
	
	@GetMapping("/getTemp/{id}")
	public List<DeviceData> getTemp(@PathVariable Integer id) {
		
		return dataRepository.getTempHum(id);
	}
	
	
}
