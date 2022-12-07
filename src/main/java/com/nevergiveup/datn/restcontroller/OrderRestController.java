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

import com.fasterxml.jackson.databind.JsonNode;
import com.nevergiveup.datn.entity.Order;
import com.nevergiveup.datn.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

	@Autowired
	OrderService service;

	@GetMapping()
	public List<Order> getAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Order getOne(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Order create(@RequestBody JsonNode orderData) {
		return service.create(orderData);
	}

	@PutMapping("{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order news) {
		return service.update(news);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
}
