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

import com.nevergiveup.datn.entity.Review;
import com.nevergiveup.datn.service.ReviewService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/review")
public class ReviewRestController {

	@Autowired
	ReviewService service;
	
	@GetMapping()
	public List<Review> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Review getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Review create(@RequestBody Review review) {
		return service.create(review);
	}
	
	@PutMapping("{id}")
	public Review update(@PathVariable("id") Integer id, @RequestBody Review review) {
		return service.update(review);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
