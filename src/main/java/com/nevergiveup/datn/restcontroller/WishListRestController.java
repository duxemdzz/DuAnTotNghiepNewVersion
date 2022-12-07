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

import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.WishListService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/wishList")
public class WishListRestController {

	@Autowired
	WishListService service;
	
	@GetMapping()
	public List<WishList> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public WishList getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public WishList create(@RequestBody WishList wishList) {
		return service.create(wishList);
	}
	
	@PutMapping("{id}")
	public WishList update(@PathVariable("id") Integer id, @RequestBody WishList wishList) {
		return service.update(wishList);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
