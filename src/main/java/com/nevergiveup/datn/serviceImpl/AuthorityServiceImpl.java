package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.AuthorityDao;
import com.nevergiveup.datn.entity.Authority;
import com.nevergiveup.datn.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDao dao;
	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public Authority findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Authority create(Authority auth) {
		return dao.saveAndFlush(auth);
	}

	@Override
	public Authority update(Authority auth) {
		return dao.saveAndFlush(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

}
