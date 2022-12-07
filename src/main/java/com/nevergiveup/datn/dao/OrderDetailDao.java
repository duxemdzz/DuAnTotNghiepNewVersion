package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT od FROM OrderDetail od where od.order.id like ?1")
	  List<OrderDetail> findByOrderId(Long id);
}
