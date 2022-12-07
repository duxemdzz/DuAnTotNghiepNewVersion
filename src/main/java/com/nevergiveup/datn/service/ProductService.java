package com.nevergiveup.datn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nevergiveup.datn.entity.Product;

public interface ProductService {
	public List<Product> findAll();
	public Product findById(Integer id);
	public Product create(Product product);
	public Product update(Product product);
	public void delete(Integer id);
	public List<Product> findByCategoryId(String cid);
	Page<Product> findByKeyword(String keyword, Pageable pageable);
	Page<Product> findByCategoryId(String cid,Pageable pa);
	List<Product> findByRandomProduct();
	public List<Product> findAllWhereDisable();
}
