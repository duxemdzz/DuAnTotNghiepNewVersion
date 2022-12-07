package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.CartItemDao;
import com.nevergiveup.datn.entity.CartItem;
import com.nevergiveup.datn.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemDao dao;

	@Override
	public List<CartItem> findAll() {
		return dao.findAll();
	}

	@Override
	public CartItem findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public CartItem create(CartItem item) {
		return dao.saveAndFlush(item);
	}

	@Override
	public CartItem update(CartItem item) {
		return dao.saveAndFlush(item);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<CartItem> findAllCartId(Integer id) {
		return dao.findAllItemByCartId(id);
	}

	@Override
	public CartItem findCartItemByCartIdAndProductId(Integer id, Integer id2) {
		return dao.findCartItemByCartIdAndProductId(id, id2);
	}
}
