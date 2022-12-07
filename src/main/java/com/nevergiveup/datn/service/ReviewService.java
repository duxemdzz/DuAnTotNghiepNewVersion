package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Review;

public interface ReviewService {
	public List<Review> findAll();
	public Review findById(Integer id);
	public Review create(Review review);
	public Review update(Review review);
	public void delete(Integer id);
	public List<Review> findReviewByProductId(Integer id);
}
