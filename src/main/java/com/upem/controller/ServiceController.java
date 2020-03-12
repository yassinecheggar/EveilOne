package com.upem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upem.models.Device;
import com.upem.models.Service;
import com.upem.models.User;
import com.upem.repository.ServiceRepository;
import com.upem.repository.UserRepository;

@RestController
@RequestMapping("/api/Service")
public class ServiceController {
	
	@Autowired
	ServiceRepository repo;
	@Autowired
	UserRepository repoUser;
	
	@PostMapping("/add")
	public String addService(@RequestBody Service service) {

		repo.save(service);
		
		return "ok";
	}

	
	@GetMapping("/getall")
	public List<Service> getallServices() {

		return (List<Service>) repo.findAll();
	}
	
	@GetMapping("/getServiceuser/{id}")
	public List<Service> getServiceuser(@PathVariable Integer id) {

		User A= repoUser.getbyId(id);
		if(A!=null) {
		
				return A.getUserServices();
				
			} 
		return (List<Service>) repo.findAll();
	}

	
	
	@GetMapping("/AddServiceUser/{id}")
	public String AddServiceUser(@PathVariable Integer id, @RequestBody Service dev) {

		User A= repoUser.getbyId(id);
		if(A!=null) {
			Service ser =  repo.getbyId(dev.getId());
			if(ser!=null) {
				List<Service> list = A.getUserServices();

				list.add(ser);
				repoUser.save(A);
				
				return "ok";
			} 
		
				
			} 
		return "not";
	}

}
