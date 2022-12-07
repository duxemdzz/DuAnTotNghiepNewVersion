package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Screen;

public interface ScreenDao extends JpaRepository<Screen, Integer>{
	@Query("SELECT s FROM Screen s WHERE s.category.id=?1")
	List<Screen> findAllByCategory(String id);
}
