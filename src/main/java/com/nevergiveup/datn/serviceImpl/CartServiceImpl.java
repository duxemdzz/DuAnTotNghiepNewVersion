package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.CartDao;
import com.nevergiveup.datn.entity.Cart;
import com.nevergiveup.datn.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDao dao;

	@Override
	public List<Cart> findAll() {
		return dao.findAll();
	}

	@Override
	public Cart findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Cart create(Cart cart) {
		return dao.saveAndFlush(cart);
	}

	@Override
	public Cart update(Cart cart) {
		return dao.saveAndFlush(cart);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Cart findCartByUsername(String username) {
		return dao.findCartByUsername(username);
	}

}
