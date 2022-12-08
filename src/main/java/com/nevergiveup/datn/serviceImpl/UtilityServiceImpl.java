package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.UtilityDao;
import com.nevergiveup.datn.entity.Utility;
import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.UtilityService;

@Service
public class UtilityServiceImpl implements UtilityService {
	@Autowired
	UtilityDao dao;

	@Override
	public List<Utility> findAll() {
		return dao.findAll();
	}

	@Override
	public Utility findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Utility create(Utility utility) {
		return dao.saveAndFlush(utility);
	}

	@Override
	public Utility update(Utility utility) {
		return dao.saveAndFlush(utility);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Utility> findAllByCategory(String id) {
		return dao.findAllByCategory(id);
	}

}
