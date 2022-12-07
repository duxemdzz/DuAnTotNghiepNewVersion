package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.Image;

public interface ImageService {
	public List<Image> findAll();
	public Image findById(Integer id);
	public Image create(Image image);
	public Image update(Image image);
	public void delete(Integer id);
	public List<Image> findByProductId(Integer id);
}
