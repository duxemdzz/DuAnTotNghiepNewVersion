package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevergiveup.datn.entity.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
