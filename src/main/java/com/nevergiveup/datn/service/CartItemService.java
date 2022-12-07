package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.CartItem;

public interface CartItemService {
	public List<CartItem> findAll();
	public CartItem findById(Integer id);
	public CartItem create(CartItem item);
	public CartItem update(CartItem item);
	public void delete(Integer id);
	List<CartItem> findAllCartId(Integer id);
	CartItem findCartItemByCartIdAndProductId(Integer id, Integer id2);
}
