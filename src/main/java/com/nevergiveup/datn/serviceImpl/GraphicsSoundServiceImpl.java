package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.GraphicsSoundDao;
import com.nevergiveup.datn.entity.GraphicsSound;
import com.nevergiveup.datn.service.GraphicsSoundService;

@Service
public class GraphicsSoundServiceImpl implements GraphicsSoundService {
	@Autowired
	GraphicsSoundDao dao;

	@Override
	public List<GraphicsSound> findAll() {
		return dao.findAll();
	}

	@Override
	public GraphicsSound findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public GraphicsSound create(GraphicsSound graphic) {
		return dao.saveAndFlush(graphic);
	}

	@Override
	public GraphicsSound update(GraphicsSound graphic) {
		return dao.saveAndFlush(graphic);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

}
