package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.UserAddress;

public interface UserAddressService {
	public List<UserAddress> findAll();
	public UserAddress findById(Integer id);
	public UserAddress create(UserAddress address);
	public UserAddress update(UserAddress address);
	public void delete(Integer id);
	public List<UserAddress> findByUserAddressByUsername(String username);
}
