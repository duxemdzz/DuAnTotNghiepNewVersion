package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Cart;

public interface CartService {
	public List<Cart> findAll();
	public Cart findById(Integer id);
	public Cart create(Cart cart);
	public Cart update(Cart cart);
	public void delete(Integer id);
	Cart findCartByUsername(String username);
	
}
