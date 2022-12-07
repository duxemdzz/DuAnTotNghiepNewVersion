package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.OrderDetail;

public interface OrderDetailService {
	public List<OrderDetail> findAll();

	public OrderDetail create(OrderDetail detail);

	public OrderDetail update(OrderDetail detail);

	public void delete(Long id);

	public OrderDetail findById(Long id);
}
