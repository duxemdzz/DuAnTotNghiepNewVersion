package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ScreenDao;
import com.nevergiveup.datn.entity.Screen;
import com.nevergiveup.datn.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {
	@Autowired
	ScreenDao dao;

	@Override
	public List<Screen> findAll() {
		return dao.findAll();
	}

	@Override
	public Screen findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Screen create(Screen screen) {
		return dao.saveAndFlush(screen);
	}

	@Override
	public Screen update(Screen screen) {
		return dao.saveAndFlush(screen);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Screen> findAllByCategory(String id) {
		return dao.findAllByCategory(id);
	}

}
