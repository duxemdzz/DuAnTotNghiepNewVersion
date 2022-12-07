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

import com.nevergiveup.datn.entity.Connect;
import com.nevergiveup.datn.service.ConnectService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/connect")
public class ConnectRestController {

	@Autowired
	ConnectService service;
	
	@GetMapping()
	public List<Connect> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Connect getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	@GetMapping("{id}")
	public List<Connect> getAllByCategory(@PathVariable("id") String id) {
		return service.findAllByCategory(id);
	}
	
	@PostMapping
	public Connect create(@RequestBody Connect connect) {
		return service.create(connect);
	}
	
	@PutMapping("{id}")
	public Connect update(@PathVariable("id") Integer id, @RequestBody Connect connect) {
		return service.update(connect);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
