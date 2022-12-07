package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevergiveup.datn.entity.Discount;

public interface DiscountDao extends JpaRepository<Discount, Integer> {

}
