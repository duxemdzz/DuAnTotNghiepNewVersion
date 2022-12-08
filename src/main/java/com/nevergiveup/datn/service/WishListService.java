package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.WishList;

public interface WishListService {
	public List<WishList> findAll();
	public WishList findById(Integer id);
	public WishList create(WishList wishlist);
	public WishList update(WishList wishlist);
	public void delete(Integer id);
	List<WishList> findWishListByUsername(String username);
}
