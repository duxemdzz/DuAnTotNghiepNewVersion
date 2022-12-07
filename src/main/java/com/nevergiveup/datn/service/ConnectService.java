package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Connect;

public interface ConnectService {
	public List<Connect> findAll();
	public Connect findById(Integer id);
	public Connect create(Connect connect);
	public Connect update(Connect connect);
	public void delete(Integer id);
	List<Connect> findAllByCategory(String id);
}
