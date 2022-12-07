package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Screen;

public interface ScreenService {
	public List<Screen> findAll();
	public Screen findById(Integer id);
	public Screen create(Screen screen);
	public Screen update(Screen screen);
	public void delete(Integer id);
	public List<Screen> findAllByCategory(String id);
}
