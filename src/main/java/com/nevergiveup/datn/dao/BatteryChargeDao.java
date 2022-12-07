package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.BatteryCharge;

public interface BatteryChargeDao extends JpaRepository<BatteryCharge, Integer>{
	@Query("SELECT b FROM BatteryCharge b WHERE b.category.id=?1")
	List<BatteryCharge> findAllByCategory(String id);
}
