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

import com.nevergiveup.datn.entity.Cart;
import com.nevergiveup.datn.service.CartService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cart")
public class CartRestController {

	@Autowired
	CartService service;
	
	@GetMapping()
	public List<Cart> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{username}")
	public Cart getOne(@PathVariable("username") String usernanme) {
		return service.findCartByUsername(usernanme);
	}
	
	@PostMapping
	public Cart create(@RequestBody Cart cart) {
		return service.create(cart);
	}
	
	@PutMapping("{id}")
	public Cart update(@PathVariable("id") Integer id, @RequestBody Cart cart) {
		return service.update(cart);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
