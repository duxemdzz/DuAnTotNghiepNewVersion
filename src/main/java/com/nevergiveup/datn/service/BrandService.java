package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Brand;

public interface BrandService {
	public List<Brand> findAll();
	public Brand findById(String id);
	public Brand create(Brand brand);
	public Brand update(Brand brand);
	public void delete(String id);
}
