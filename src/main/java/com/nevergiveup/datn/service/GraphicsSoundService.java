package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.GraphicsSound;

public interface GraphicsSoundService {
	public List<GraphicsSound> findAll();
	public GraphicsSound findById(Integer id);
	public GraphicsSound create(GraphicsSound sound);
	public GraphicsSound update(GraphicsSound sound);
	public void delete(Integer id);
	
}
