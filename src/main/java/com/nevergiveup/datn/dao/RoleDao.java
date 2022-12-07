package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Role;



public interface RoleDao extends JpaRepository<Role, String> {
	@Query("select r from Role r where id = User ")
	Role findRole();
}
