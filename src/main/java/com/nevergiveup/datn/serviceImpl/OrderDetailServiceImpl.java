package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.OrderDetailDao;
import com.nevergiveup.datn.entity.OrderDetail;
import com.nevergiveup.datn.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailDao dao;

	@Override
	public List<OrderDetail> findAll() {
		return dao.findAll();
	}

	@Override
	public OrderDetail findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public OrderDetail create(OrderDetail detail) {
		return dao.saveAndFlush(detail);
	}

	@Override
	public OrderDetail update(OrderDetail detail) {
		return dao.saveAndFlush(detail);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

}
