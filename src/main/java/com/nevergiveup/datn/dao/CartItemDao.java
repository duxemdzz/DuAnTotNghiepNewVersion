package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	@Query("SELECT item FROM CartItem item WHERE item.cart.id = ?1")
	List<CartItem>  findAllItemByCartId(Integer id);
	@Query("SELECT item FROM CartItem item WHERE item.cart.id = ?1 and item.product.id = ?2")
	CartItem  findCartItemByCartIdAndProductId(Integer id, Integer id2);
}
