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

import com.nevergiveup.datn.entity.OrderDetail;
import com.nevergiveup.datn.service.OrderDetailService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderDetail")
public class OrderDetailRestController {

	@Autowired
	OrderDetailService service;
	
	@GetMapping()
	public List<OrderDetail> getAll() {
		return service.findAll();
	}
	
	/*
	 * @GetMapping("{id}") public OrderDetail getOne(@PathVariable("id") Long id) {
	 * return service.findById(id); }
	 */
	
	@PostMapping
	public OrderDetail create(@RequestBody OrderDetail news) {
		return service.create(news);
	}
	
	@PutMapping("{id}")
	public OrderDetail update(@PathVariable("id") Integer id, @RequestBody OrderDetail news) {
		return service.update(news);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		 service.delete(id);
	}
}
