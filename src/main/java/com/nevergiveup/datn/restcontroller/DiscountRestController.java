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

import com.nevergiveup.datn.entity.Discount;
import com.nevergiveup.datn.service.DiscountService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/discount")
public class DiscountRestController {

	@Autowired
	DiscountService service;
	
	@GetMapping()
	public List<Discount> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Discount getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Discount create(@RequestBody Discount discount) {
		return service.create(discount);
	}
	
	@PutMapping("{id}")
	public Discount update(@PathVariable("id") Integer id, @RequestBody Discount discount) {
		return service.update(discount);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
