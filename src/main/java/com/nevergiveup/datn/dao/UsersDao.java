package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Users;



public interface UsersDao extends JpaRepository<Users, String>{
	@Query("SELECT u FROM Users u WHERE u.username = ?1")
	Users findByUsername(String username);
}
