package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ProductDao;
import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao dao;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Product create(Product product) {
		return dao.saveAndFlush(product);
	}

	@Override
	public Product update(Product product) {
		return dao.saveAndFlush(product);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return dao.findByCategoryId(cid);
	}

	@Override
	public Page<Product> findByKeyword(String keyword, Pageable pageable) {
		return dao.findByKeyword(keyword, pageable);
	}

	@Override
	public Page<Product> findByCategoryId(String cid, Pageable pa) {
		return dao.findByCategoryId(cid, pa);
	}

	@Override
	public List<Product> findByRandomProduct() {		
		return dao.findByRandomProduct();
	}
	
	@Override
	public List<Product> findAllWhereDisable() {
		return dao.findAllWhereDisable();
	}


}
