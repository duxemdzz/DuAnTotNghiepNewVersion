package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.ProductConfigurationLaptop;
import com.nevergiveup.datn.entity.ProductConfigurationPhone;


public interface CompareConfigService {
	List<ProductConfigurationPhone> getProductConfigurationPhone();
	
	List<ProductConfigurationLaptop> getProductConfigurationLaptop();

	void addPhone(int id);

	void removePhone(int id);
	
	void addLaptop(int id);

	void removeLaptop(int id);
}
