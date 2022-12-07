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

import com.nevergiveup.datn.entity.Payment;
import com.nevergiveup.datn.service.PaymentService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/payment")
public class PaymentRestController {

	@Autowired
	PaymentService service;
	
	@GetMapping()
	public List<Payment> getAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Payment getOne(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Payment create(@RequestBody Payment payment) {
		return service.create(payment);
	}
	
	@PutMapping("{id}")
	public Payment update(@PathVariable("id") Integer id, @RequestBody Payment payment) {
		return service.update(payment);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 service.delete(id);
	}
}
