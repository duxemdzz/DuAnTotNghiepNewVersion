package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.TagProduct;

public interface TagProductService {
	public List<TagProduct> findAll();
	public TagProduct findById(Integer id);
	public TagProduct create(TagProduct tag);
	public TagProduct update(TagProduct tag);
	public void delete(Integer id);
}
