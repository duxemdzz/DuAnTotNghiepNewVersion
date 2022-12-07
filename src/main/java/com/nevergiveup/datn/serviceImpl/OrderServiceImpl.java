package com.nevergiveup.datn.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevergiveup.datn.dao.OrderDAO;
import com.nevergiveup.datn.dao.OrderDetailDao;
import com.nevergiveup.datn.entity.Order;
import com.nevergiveup.datn.entity.OrderDetail;
import com.nevergiveup.datn.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDao ddao;

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Order create(JsonNode orderData) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData,  Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
			ddao.saveAll(details);
				return order;
	}

	@Override
	public Order update(Order order) {
		return dao.saveAndFlush(order);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

}
