package com.upem.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upem.models.Device;
import com.upem.models.User;
import com.upem.repository.DeviceRepository;
import com.upem.repository.UserRepository;

@RestController
@RequestMapping("/api/Device")
public class DeviceController {

	@Autowired
	UserRepository repo;
	@Autowired
	DeviceRepository repoDev;

	@PutMapping("/AddUserDevice/{id}")
	public String AddUserDevice(@PathVariable Integer id, @RequestBody Device dev)
	{
		User A= repo.getbyId(id);
		if(A!=null) {
			Device D =  repoDev.getbyId(dev.getIdf(), dev.getCle());
			if(D!=null) {
				List<Device> lisDevices = A.getUserDevices();

				lisDevices.add(D);
				repo.save(A);
				return "ok";
			} 
		}
		return "not";
	}

	@PostMapping("/add")
	public String addDevice(@RequestBody Device dev) {

		repoDev.save(dev);
		return "ok";
	}

	@GetMapping("/getDeviceUser/{id}")
	public List<Device> getDeviceUser(@PathVariable Integer id) {

		return repo.getbyId(id).getUserDevices();
	}
	
	
	@GetMapping("/gettest")
	public Device gettest() {

		return repoDev.getbyIdMAc("yas");
	}

	@PostMapping("/deleteDeviceUser/{id}")
	public String delete(@PathVariable Integer id,@RequestBody Device device  ) {

		User A= repo.getbyId(id);
		List<Device> devices  =   A.getUserDevices();
		boolean stat =false;

		for (Iterator<Device> iter = devices.listIterator(); iter.hasNext(); ) {
			Device D = iter.next();
			if(device.getId() == D.getId()) {

				iter.remove();
				stat = true;
				repo.save(A);

			}
		}

		if(stat == true)return "ok";

		return "not";
	}

}
