package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.News;

public interface NewService {
	public List<News> findAll();
	public News findById(Integer id);
	public News create(News news);
	public News update(News news);
	public void delete(Integer id);
}
