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

import com.nevergiveup.datn.entity.Brand;
import com.nevergiveup.datn.service.BrandService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandRestController {

	@Autowired
	BrandService service;
	
	@GetMapping()
	public List<Brand> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Brand getOne(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Brand create(@RequestBody Brand brand) {
		return service.create(brand);
	}
	
	@PutMapping("{id}")
	public Brand update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
		return service.update(brand);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		 service.delete(id);
	}
}
