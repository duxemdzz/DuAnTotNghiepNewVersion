package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Connect;

public interface ConnectDao extends JpaRepository<Connect, Integer>{
	@Query("SELECT c FROM Connect c WHERE c.category.id=?1")
	List<Connect> findAllByCategory(String id);
}
