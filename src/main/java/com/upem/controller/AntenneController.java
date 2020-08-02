package com.upem.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upem.models.Antenne;
import com.upem.models.User;
import com.upem.repository.AntenneRepository;
import com.upem.repository.UserRepository;

@RestController
@RequestMapping("/api/antenne")
public class AntenneController {

	@Autowired
	AntenneRepository repo;
	
	@GetMapping("/test")
	public String addAlien()
	{
		return "test Antenne";
	}
	
	
	@GetMapping("/getAll") // get all antennas informations
	public List<Antenne> getAll()
	{
		
		return (List<Antenne>) repo.findAll();
	}
	
	@PostMapping("/add") // add new antenna
	public void add(@RequestBody Antenne ant)
	{
		repo.save(ant);
		
	}
	
	@PutMapping("/edit/{id}")// edit antenna
	public void edit(@PathVariable Integer id, @RequestBody Antenne ant)
	{
		 Antenne A= repo.getbyId(id);
		 if(A!=null) {
			 ant.setId(id);
			 A = ant; 
			 repo.save(A);
		 }
	}
	
	
	
}
