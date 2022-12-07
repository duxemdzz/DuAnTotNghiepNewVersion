package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {
	@Query("SELECT cart FROM Cart cart WHERE cart.users.username = ?1")
	Cart findCartByUsername(String username);
}
