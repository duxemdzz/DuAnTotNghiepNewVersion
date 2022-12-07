package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ProductConfigurationPhoneDao;
import com.nevergiveup.datn.entity.ProductConfigurationPhone;
import com.nevergiveup.datn.service.ConfigPhoneService;

@Service
public class ConfigPhoneServiceImpl implements ConfigPhoneService {
	@Autowired
	ProductConfigurationPhoneDao dao;

	@Override
	public List<ProductConfigurationPhone> findAll() {
		return dao.findAll();
	}

	@Override
	public ProductConfigurationPhone findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public ProductConfigurationPhone create(ProductConfigurationPhone configPhone) {
		return dao.saveAndFlush(configPhone);
	}

	@Override
	public ProductConfigurationPhone update(ProductConfigurationPhone configPhone) {
		return dao.saveAndFlush(configPhone);
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
	public ProductConfigurationPhone findConfigByProduct(Integer id) {
		return dao.findConfigByProduct(id);
	}

}
