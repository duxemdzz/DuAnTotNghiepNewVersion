package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.WishList;

public interface WishListDao extends JpaRepository<WishList, Integer>{
	@Query("SELECT wl FROM WishList wl WHERE wl.users.username = ?1")
	List<WishList> findWishListByUsername(String username);
}
