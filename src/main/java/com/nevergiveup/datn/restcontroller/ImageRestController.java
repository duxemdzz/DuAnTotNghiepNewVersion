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

import com.nevergiveup.datn.entity.Image;
import com.nevergiveup.datn.service.ImageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/image")
public class ImageRestController {

	@Autowired
	ImageService service;

	@GetMapping()
	public List<Image> getAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Image getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}

	@GetMapping("/product/{id}")
	public List<Image> getByProductId(@PathVariable("id") Integer id) {
		return service.findByProductId(id);
	}

	@PostMapping
	public Image create(@RequestBody Image image) {
		return service.create(image);
	}

	@PutMapping("{id}")
	public Image update(@PathVariable("id") Integer id, @RequestBody Image image) {
		return service.update(image);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}
}
