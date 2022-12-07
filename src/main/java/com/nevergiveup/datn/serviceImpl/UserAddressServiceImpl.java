package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.UserAddressDao;
import com.nevergiveup.datn.entity.UserAddress;
import com.nevergiveup.datn.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService {
	@Autowired
	UserAddressDao dao;

	@Override
	public List<UserAddress> findAll() {
		return dao.findAll();
	}

	@Override
	public UserAddress findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public UserAddress create(UserAddress address) {
		return dao.saveAndFlush(address);
	}

	@Override
	public UserAddress update(UserAddress address) {
		return dao.saveAndFlush(address);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<UserAddress> findByUserAddressByUsername(String username) {
		return dao.findByUserAddressByUsername(username);
	}

}
