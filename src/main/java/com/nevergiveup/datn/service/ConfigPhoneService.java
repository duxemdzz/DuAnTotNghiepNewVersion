package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.ProductConfigurationPhone;

public interface ConfigPhoneService {
	public List<ProductConfigurationPhone> findAll();
	public ProductConfigurationPhone findById(Integer id);
	public ProductConfigurationPhone create(ProductConfigurationPhone configPhone);
	public ProductConfigurationPhone update(ProductConfigurationPhone configPhone);
	public void delete(Integer id);
	boolean existsById(Integer id);
	public ProductConfigurationPhone findConfigByProduct(Integer id);
}
