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

import com.nevergiveup.datn.entity.GraphicsSound;
import com.nevergiveup.datn.service.GraphicsSoundService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/graphicsSound")
public class GraphicsSoundRestController {

	@Autowired
	GraphicsSoundService service;
	
	@GetMapping()
	public List<GraphicsSound> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public GraphicsSound getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public GraphicsSound create(@RequestBody GraphicsSound graphic) {
		return service.create(graphic);
	}
	
	@PutMapping("{id}")
	public GraphicsSound update(@PathVariable("id") Integer id, @RequestBody GraphicsSound graphic) {
		return service.update(graphic);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
