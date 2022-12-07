package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.DiscountDao;
import com.nevergiveup.datn.entity.Discount;
import com.nevergiveup.datn.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	DiscountDao dao;

	@Override
	public List<Discount> findAll() {
		return dao.findAll();
	}

	@Override
	public Discount findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Discount create(Discount discount) {
		return dao.saveAndFlush(discount);
	}

	@Override
	public Discount update(Discount discount) {
		return dao.saveAndFlush(discount);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
