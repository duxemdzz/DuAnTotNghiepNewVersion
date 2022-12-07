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

import com.nevergiveup.datn.entity.Authority;
import com.nevergiveup.datn.service.AuthorityService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {

	@Autowired
	AuthorityService service;
	
	@GetMapping()
	public List<Authority> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Authority getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Authority create(@RequestBody Authority auth) {
		return service.create(auth);
	}
	
	@PutMapping("{id}")
	public Authority update(@PathVariable("id") Integer id, @RequestBody Authority auth) {
		return service.update(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
