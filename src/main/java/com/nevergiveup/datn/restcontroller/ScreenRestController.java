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

import com.nevergiveup.datn.entity.Screen;
import com.nevergiveup.datn.service.ScreenService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/screen")
public class ScreenRestController {

	@Autowired
	ScreenService service;
	
	@GetMapping()
	public List<Screen> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Screen getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	@GetMapping("{id}")
	public List<Screen> getByCategory(@PathVariable("id") String id) {
		return service.findAllByCategory(id);
	}
	
	@PostMapping
	public Screen create(@RequestBody Screen screen) {
		return service.create(screen);
	}
	
	@PutMapping("{id}")
	public Screen update(@PathVariable("id") Integer id, @RequestBody Screen screen) {
		return service.update(screen);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
