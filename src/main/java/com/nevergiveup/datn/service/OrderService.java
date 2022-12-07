package com.nevergiveup.datn.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.nevergiveup.datn.entity.Order;

public interface OrderService {
	public List<Order> findAll();
	public Order findById(Long id);
	public Order create(JsonNode orderData);
	public Order update(Order order);
	public void delete(Long id);
	List<Order> findByUsername(String username);
}
