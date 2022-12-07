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

import com.nevergiveup.datn.entity.TagProduct;
import com.nevergiveup.datn.service.TagProductService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/tagProduct")
public class TagProductRestController {

	@Autowired
	TagProductService service;
	
	@GetMapping()
	public List<TagProduct> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public TagProduct getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public TagProduct create(@RequestBody TagProduct tag) {
		return service.create(tag);
	}
	
	@PutMapping("{id}")
	public TagProduct update(@PathVariable("id") Integer id, @RequestBody TagProduct tag) {
		return service.update(tag);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
