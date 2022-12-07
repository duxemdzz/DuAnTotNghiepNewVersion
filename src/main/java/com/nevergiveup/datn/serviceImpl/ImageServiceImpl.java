package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.ImageDao;
import com.nevergiveup.datn.entity.Image;
import com.nevergiveup.datn.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageDao dao;

	@Override
	public List<Image> findAll() {
		return dao.findAll();
	}

	@Override
	public Image findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Image create(Image image) {
		return dao.saveAndFlush(image);
	}

	@Override
	public Image update(Image image) {
		return dao.saveAndFlush(image);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Image> findByProductId(Integer id) {
		return dao.findByProductId(id);	
	}
}
