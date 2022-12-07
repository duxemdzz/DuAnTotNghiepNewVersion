package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nevergiveup.datn.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	@Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
	Page<Product> findByKeyword(String keyword, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	Page<Product> findByCategoryId(String cid, Pageable pageable);
	
	@Query(value ="select top 5 * from Products ORDER BY NEWid()", nativeQuery = true)
	List<Product> findByRandomProduct();
	
	@Query("SELECT p FROM Product p WHERE p.isDisable = false")
	List<Product> findAllWhereDisable();
}
