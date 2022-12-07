package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.ProductConfigurationLaptop;

public interface ProductConfigurationLaptopDao extends JpaRepository<ProductConfigurationLaptop, Integer> {
	@Query("SELECT c FROM ProductConfigurationLaptop c WHERE c.product.id = ?1")
	ProductConfigurationLaptop findConfigByProduct(Integer id);
}
