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

import com.nevergiveup.datn.entity.News;
import com.nevergiveup.datn.service.NewService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/news")
public class NewRestController {

	@Autowired
	NewService service;
	
	@GetMapping()
	public List<News> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public News getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public News create(@RequestBody News news) {
		return service.create(news);
	}
	
	@PutMapping("{id}")
	public News update(@PathVariable("id") Integer id, @RequestBody News news) {
		return service.update(news);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
