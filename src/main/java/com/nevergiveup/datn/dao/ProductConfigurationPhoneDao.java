package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.ProductConfigurationPhone;

public interface ProductConfigurationPhoneDao extends JpaRepository<ProductConfigurationPhone, Integer> {
	@Query("SELECT c FROM ProductConfigurationPhone c WHERE c.product.id = ?1")
	ProductConfigurationPhone findConfigByProduct(Integer id);
}
