package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ReviewDao;
import com.nevergiveup.datn.entity.Review;
import com.nevergiveup.datn.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDao dao;
	@Override
	public List<Review> findAll() {
		return dao.findAll();
	}

	@Override
	public Review findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Review create(Review review) {
		return dao.saveAndFlush(review);
	}

	@Override
	public Review update(Review review) {
		return dao.saveAndFlush(review);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Review> findReviewByProductId(Integer id) {
		return dao.findReviewByProductId(id);
	}

}
