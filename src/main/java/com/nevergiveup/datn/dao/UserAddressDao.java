package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.UserAddress;

public interface UserAddressDao extends JpaRepository<UserAddress, Integer>{
	@Query("Select ua from UserAddress ua where ua.users.username = ?1")
	public List<UserAddress> findByUserAddressByUsername(String username);
}
