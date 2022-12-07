package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.NewDao;
import com.nevergiveup.datn.entity.News;
import com.nevergiveup.datn.service.NewService;
@Service
public class NewServiceImpl implements NewService{
	@Autowired
	NewDao dao;
	@Override
	public List<News> findAll() {
		return dao.findAll();
	}

	@Override
	public News findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public News create(News news) {
		return dao.saveAndFlush(news);
	}

	@Override
	public News update(News news) {
		return dao.saveAndFlush(news);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

}
