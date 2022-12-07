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

import com.nevergiveup.datn.entity.ProductConfigurationPhone;
import com.nevergiveup.datn.service.ConfigPhoneService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/configPhone")
public class ConfigPhoneRestController {

	@Autowired
	ConfigPhoneService service;
	
	@GetMapping()
	public List<ProductConfigurationPhone> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public ProductConfigurationPhone getOne(@PathVariable("id") Integer id) {
		return service.findConfigByProduct(id);
	}
	
	@PostMapping
	public ProductConfigurationPhone create(@RequestBody ProductConfigurationPhone configPhone) {
		return service.create(configPhone);
	}
	
	@PutMapping("{id}")
	public ProductConfigurationPhone update(@PathVariable("id") Integer id, @RequestBody ProductConfigurationPhone configPhone) {
		return service.update(configPhone);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
