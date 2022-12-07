package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.TagProductDao;
import com.nevergiveup.datn.entity.TagProduct;
import com.nevergiveup.datn.service.TagProductService;

@Service
public class TagProductServiceImpl implements TagProductService {
	@Autowired
	TagProductDao dao;

	@Override
	public List<TagProduct> findAll() {
		return dao.findAll();
	}

	@Override
	public TagProduct findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public TagProduct create(TagProduct tag) {
		return dao.saveAndFlush(tag);
	}

	@Override
	public TagProduct update(TagProduct tag) {
		return dao.saveAndFlush(tag);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
