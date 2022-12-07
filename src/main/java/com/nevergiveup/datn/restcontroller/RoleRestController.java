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

import com.nevergiveup.datn.entity.Role;
import com.nevergiveup.datn.service.RoleService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/role")
public class RoleRestController {

	@Autowired
	RoleService service;
	
	@GetMapping()
	public List<Role> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Role getOne(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Role create(@RequestBody Role role) {
		return service.create(role);
	}
	
	@PutMapping("{id}")
	public Role update(@PathVariable("id") Integer id, @RequestBody Role role) {
		return service.update(role);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		 service.delete(id);
	}
}
