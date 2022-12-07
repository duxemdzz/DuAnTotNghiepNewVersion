package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.GeneralInformation;

public interface GeneralInformationDao extends JpaRepository<GeneralInformation, Integer> {
	@Query("SELECT g FROM GeneralInformation g WHERE g.category.id=?1")
	List<GeneralInformation> findAllByCategory(String id);
}
