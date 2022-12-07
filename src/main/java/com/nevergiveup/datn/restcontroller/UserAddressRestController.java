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

import com.nevergiveup.datn.entity.UserAddress;
import com.nevergiveup.datn.service.UserAddressService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/userAddress")
public class UserAddressRestController {

	@Autowired
	UserAddressService service;
	
	@GetMapping()
	public List<UserAddress> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{username}")
	public List<UserAddress> getAllUserAddressByUsername(@PathVariable("username") String username) {
		return service.findByUserAddressByUsername(username);
	}
	@GetMapping("get/{id}")
	public UserAddress getAllUserAddressByUsername(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public UserAddress create(@RequestBody UserAddress address) {
		return service.create(address);
	}
	
	@PutMapping("{id}")
	public UserAddress update(@PathVariable("id") Integer id, @RequestBody UserAddress address) {
		return service.update(address);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
