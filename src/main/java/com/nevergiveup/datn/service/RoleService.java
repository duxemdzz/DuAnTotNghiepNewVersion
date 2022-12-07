package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Role;

public interface RoleService {
	public List<Role> findAll();
	public Role findById(String id);
	public Role create(Role role);
	public Role update(Role role);
	public void delete(String id);
}
