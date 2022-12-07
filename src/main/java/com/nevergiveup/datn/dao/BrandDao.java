package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevergiveup.datn.entity.Brand;

public interface BrandDao extends JpaRepository<Brand, String> {

}
