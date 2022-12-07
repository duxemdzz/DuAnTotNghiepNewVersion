package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Order;
import com.nevergiveup.datn.entity.OrderDetail;



public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.users.username=?1")
	List<Order> findByUsername(String username);
	
	@Query("SELECT od FROM OrderDetail od where od.order.id like ?1")
	  List<OrderDetail> findByOrderId(Long id);
}
