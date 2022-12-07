package com.nevergiveup.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevergiveup.datn.entity.News;

public interface NewDao extends JpaRepository<News, Integer> {

}
