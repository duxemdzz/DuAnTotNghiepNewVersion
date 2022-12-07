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

import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.service.ProductService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService pService;
	
	@GetMapping()
	public List<Product> getAll() {
		return pService.findAll();
	}
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return pService.findById(id);
	}
	
	@GetMapping("category/{id}")
	public List<Product> getProductByCategory(@PathVariable("id") String id) {
		return pService.findByCategoryId(id);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		product.setIsDisable(false);
		product.setAvaliable(true);
		return pService.create(product);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return pService.update(product);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		 pService.delete(id);
	}
}
