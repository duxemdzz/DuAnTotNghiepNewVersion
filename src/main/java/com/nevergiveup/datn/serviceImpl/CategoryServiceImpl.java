package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.CategoryDao;
import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao dao;

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public Category findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public Category create(Category category) {
		return dao.saveAndFlush(category);
	}

	@Override
	public Category update(Category category) {
		return dao.saveAndFlush(category);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

}
