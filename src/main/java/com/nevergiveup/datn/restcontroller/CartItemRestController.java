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

import com.nevergiveup.datn.entity.CartItem;
import com.nevergiveup.datn.service.CartItemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cartItem")
public class CartItemRestController {

	@Autowired
	CartItemService service;

	@GetMapping()
	public List<CartItem> getAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public List<CartItem> getAll(@PathVariable("id") Integer id) {
		return service.findAllCartId(id);
	}

	@GetMapping("{id}/{id2}")
	public CartItem getOne(@PathVariable("id") Integer id, @PathVariable("id2") Integer id2) {
		return service.findCartItemByCartIdAndProductId(id, id2);
	}

	@PostMapping
	public CartItem create(@RequestBody CartItem cartItem) {
		return service.create(cartItem);
	}

	@PutMapping("{id}")
	public CartItem update(@PathVariable("id") Integer id, @RequestBody CartItem cartItem) {
		return service.update(cartItem);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			System.err.println(e);
		}

	}
}
