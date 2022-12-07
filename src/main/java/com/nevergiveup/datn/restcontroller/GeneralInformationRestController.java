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

import com.nevergiveup.datn.entity.GeneralInformation;
import com.nevergiveup.datn.service.GeneralInformationService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/generalInformation")
public class GeneralInformationRestController {

	@Autowired
	GeneralInformationService service;
	
	@GetMapping()
	public List<GeneralInformation> getAll() {
		return service.findAll();
	}
	
	@GetMapping("/id/{id}")
	public GeneralInformation getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	@GetMapping("{id}")
	public List<GeneralInformation> getAllByCategory(@PathVariable("id") String id) {
		return service.findAllByCategory(id);
	}
	
	@PostMapping
	public GeneralInformation create(@RequestBody GeneralInformation gener) {
		return service.create(gener);
	}
	
	@PutMapping("{id}")
	public GeneralInformation update(@PathVariable("id") Integer id, @RequestBody GeneralInformation gener) {
		return service.update(gener);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
