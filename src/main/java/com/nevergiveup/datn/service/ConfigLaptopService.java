package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.ProductConfigurationLaptop;

public interface ConfigLaptopService {
	public List<ProductConfigurationLaptop> findAll();
	public ProductConfigurationLaptop findById(Integer id);
	public ProductConfigurationLaptop create(ProductConfigurationLaptop configLaptop);
	public ProductConfigurationLaptop update(ProductConfigurationLaptop configLaptop);
	public void delete(Integer id);
	boolean existsById(Integer id);
	public ProductConfigurationLaptop findConfigByProduct(Integer id);
}
