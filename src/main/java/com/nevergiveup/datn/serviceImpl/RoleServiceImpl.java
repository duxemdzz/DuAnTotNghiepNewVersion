package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.RoleDao;
import com.nevergiveup.datn.entity.Role;
import com.nevergiveup.datn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDao dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public Role create(Role role) {
		return dao.saveAndFlush(role);
	}

	@Override
	public Role update(Role role) {
		return dao.saveAndFlush(role);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

}
