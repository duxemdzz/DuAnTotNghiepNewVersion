package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Utility;

public interface UtilityDao extends JpaRepository<Utility, Integer>{
	@Query("SELECT u FROM Utility u WHERE u.category.id=?1")
	List<Utility> findAllByCategory(String id);
}
