package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.WishListDao;
import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {
	@Autowired
	WishListDao dao;

	@Override
	public List<WishList> findAll() {
		return dao.findAll();
	}

	@Override
	public WishList findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public WishList create(WishList wishlist) {
		return dao.saveAndFlush(wishlist);
	}

	@Override
	public WishList update(WishList wishlist) {
		return dao.saveAndFlush(wishlist);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<WishList> findWishListByUsername(String username) {
		return dao.findWishListByUsername(username);
	}

}
