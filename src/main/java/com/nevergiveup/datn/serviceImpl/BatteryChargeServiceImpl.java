package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.BatteryChargeDao;
import com.nevergiveup.datn.entity.BatteryCharge;
import com.nevergiveup.datn.service.BatteryChargeService;

@Service
public class BatteryChargeServiceImpl implements BatteryChargeService {
	@Autowired
	BatteryChargeDao dao;

	@Override
	public List<BatteryCharge> findAll() {
		return dao.findAll();
	}

	@Override
	public BatteryCharge findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public BatteryCharge create(BatteryCharge batteryChange) {
		return dao.saveAndFlush(batteryChange);
	}

	@Override
	public BatteryCharge update(BatteryCharge batteryChange) {
		return dao.saveAndFlush(batteryChange);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<BatteryCharge> findAllByCategory(String id) {
		return dao.findAllByCategory(id);
	}

}
