package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ConnectDao;
import com.nevergiveup.datn.entity.Connect;
import com.nevergiveup.datn.service.ConnectService;

@Service
public class ConnectServiceImpl implements ConnectService {
	@Autowired
	ConnectDao dao;

	@Override
	public List<Connect> findAll() {
		return dao.findAll();
	}

	@Override
	public Connect findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Connect create(Connect connect) {
		return dao.saveAndFlush(connect);
	}

	@Override
	public Connect update(Connect connect) {
		return dao.saveAndFlush(connect);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Connect> findAllByCategory(String id) {
		return dao.findAllByCategory(id);
	}

}
