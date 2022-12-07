package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Payment;

public interface PaymentService {
	public List<Payment> findAll();
	public Payment findById(Integer id);
	public Payment create(Payment payment);
	public Payment update(Payment payment);
	public void delete(Integer id);
}
