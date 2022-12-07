package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ProductConfigurationLaptopDao;
import com.nevergiveup.datn.entity.ProductConfigurationLaptop;
import com.nevergiveup.datn.service.ConfigLaptopService;

@Service
public class ConfigLaptopServiceImpl implements ConfigLaptopService {
	@Autowired
	ProductConfigurationLaptopDao dao;

	@Override
	public List<ProductConfigurationLaptop> findAll() {
		return dao.findAll();
	}

	@Override
	public ProductConfigurationLaptop findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public ProductConfigurationLaptop create(ProductConfigurationLaptop configLaptop) {
		return dao.saveAndFlush(configLaptop);
	}

	@Override
	public ProductConfigurationLaptop update(ProductConfigurationLaptop configLaptop) {
		return dao.saveAndFlush(configLaptop);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public ProductConfigurationLaptop findConfigByProduct(Integer id) {
		return dao.findConfigByProduct(id);
	}

}
