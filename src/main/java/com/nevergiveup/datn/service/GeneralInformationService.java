package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.GeneralInformation;

public interface GeneralInformationService {
	public List<GeneralInformation> findAll();
	public GeneralInformation findById(Integer id);
	public GeneralInformation create(GeneralInformation genner);
	public GeneralInformation update(GeneralInformation genner);
	public void delete(Integer id);
	public List<GeneralInformation> findAllByCategory(String id);
}
