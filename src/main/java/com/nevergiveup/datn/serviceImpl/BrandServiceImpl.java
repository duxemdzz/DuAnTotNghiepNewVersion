package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.BrandDao;
import com.nevergiveup.datn.entity.Brand;
import com.nevergiveup.datn.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandDao dao;
	@Override
	public List<Brand> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Brand create(Brand brand) {
		return dao.saveAndFlush(brand);
	}

	@Override
	public Brand update(Brand brand) {
		return dao.saveAndFlush(brand);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}



	@Override
	public Brand findById(String id) {
		return dao.findById(id).get();
	}

}
