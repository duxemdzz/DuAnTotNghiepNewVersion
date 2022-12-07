package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevergiveup.datn.entity.Category;

public interface CategoryDao extends JpaRepository<Category, String> {

}
