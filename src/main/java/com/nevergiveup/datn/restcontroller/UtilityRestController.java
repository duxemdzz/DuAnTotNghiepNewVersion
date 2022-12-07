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

import com.nevergiveup.datn.entity.Utility;
import com.nevergiveup.datn.service.UtilityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/utility")
public class UtilityRestController {

	@Autowired
	UtilityService service;
	
	@GetMapping()
	public List<Utility> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Utility getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	@GetMapping("{id}")
	public List<Utility> getAllByCategory(@PathVariable("id") String id) {
		return service.findAllByCategory(id);
	}
	
	@PostMapping
	public Utility create(@RequestBody Utility utility) {
		return service.create(utility);
	}
	
	@PutMapping("{id}")
	public Utility update(@PathVariable("id") Integer id, @RequestBody Utility utility) {
		return service.update(utility);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
