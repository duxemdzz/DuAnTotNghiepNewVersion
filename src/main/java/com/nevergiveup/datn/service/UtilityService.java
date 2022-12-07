package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Utility;

public interface UtilityService {
	public List<Utility> findAll();
	public Utility findById(Integer id);
	public Utility create(Utility utility);
	public Utility update(Utility utility);
	public void delete(Integer id);
	public List<Utility> findAllByCategory(String id);
}
