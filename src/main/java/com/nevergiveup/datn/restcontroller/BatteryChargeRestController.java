package com.nevergiveup.datn.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevergiveup.datn.entity.BatteryCharge;
import com.nevergiveup.datn.service.BatteryChargeService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/batteryCharges")
public class BatteryChargeRestController {

	@Autowired
	BatteryChargeService service;
	
	@GetMapping()
	public List<BatteryCharge> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/id/{id}")
	public BatteryCharge getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	@GetMapping("{id}")
	public List<BatteryCharge> getAllByCategory(@PathVariable("id") String id) {
		return service.findAllByCategory(id);
	}
	
	@PostMapping
	public BatteryCharge create(@RequestBody BatteryCharge battery) {
		return service.create(battery);
	}
	
	@PutMapping("{id}")
	public BatteryCharge update(@PathVariable("id") Integer id, @RequestBody BatteryCharge battery) {
		return service.update(battery);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
