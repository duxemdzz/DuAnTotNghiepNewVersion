package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {
	@Query("Select rv from Review rv  where rv.product.id = ?1 ")
	List<Review> findReviewByProductId(Integer id);
}
