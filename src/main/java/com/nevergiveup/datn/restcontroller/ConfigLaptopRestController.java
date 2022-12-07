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

import com.nevergiveup.datn.entity.ProductConfigurationLaptop;
import com.nevergiveup.datn.service.ConfigLaptopService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/configLaptop")
public class ConfigLaptopRestController {

	@Autowired
	ConfigLaptopService service;
	
	@GetMapping()
	public List<ProductConfigurationLaptop> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public ProductConfigurationLaptop getOne(@PathVariable("id") Integer id) {
		return service.findConfigByProduct(id);
	}
	
	@PostMapping
	public ProductConfigurationLaptop create(@RequestBody ProductConfigurationLaptop configLaptop) {
		return service.create(configLaptop);
	}
	
	@PutMapping("{id}")
	public ProductConfigurationLaptop update(@PathVariable("id") Integer id, @RequestBody ProductConfigurationLaptop configLaptop) {
		return service.update(configLaptop);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
