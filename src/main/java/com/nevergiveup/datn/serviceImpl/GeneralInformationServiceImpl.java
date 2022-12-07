package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.GeneralInformationDao;
import com.nevergiveup.datn.entity.GeneralInformation;
import com.nevergiveup.datn.service.GeneralInformationService;

@Service
public class GeneralInformationServiceImpl implements GeneralInformationService {
	@Autowired
	GeneralInformationDao dao;

	@Override
	public List<GeneralInformation> findAll() {
		return dao.findAll();
	}

	@Override
	public GeneralInformation findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public GeneralInformation create(GeneralInformation gener) {
		return dao.saveAndFlush(gener);
	}

	@Override
	public GeneralInformation update(GeneralInformation gener) {
		return dao.saveAndFlush(gener);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<GeneralInformation> findAllByCategory(String id) {
		return dao.findAllByCategory(id);
	}

}
