package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Discount;

public interface DiscountService {
	public List<Discount> findAll();
	public Discount findById(Integer id);
	public Discount create(Discount discount);
	public Discount update(Discount discount);
	public void delete(Integer id);
}
