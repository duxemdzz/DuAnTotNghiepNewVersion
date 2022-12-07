package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.PaymentDao;
import com.nevergiveup.datn.entity.Payment;
import com.nevergiveup.datn.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentDao dao;

	@Override
	public List<Payment> findAll() {
		return dao.findAll();
	}

	@Override
	public Payment findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Payment create(Payment payment) {
		return dao.saveAndFlush(payment);
	}

	@Override
	public Payment update(Payment payment) {
		return dao.saveAndFlush(payment);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
