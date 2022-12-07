package com.nevergiveup.datn.service;

import java.util.List;

import com.nevergiveup.datn.entity.BatteryCharge;

public interface BatteryChargeService {
	public List<BatteryCharge> findAll();
	public BatteryCharge findById(Integer id);
	public BatteryCharge create(BatteryCharge batteryChange);
	public BatteryCharge update(BatteryCharge batteryChange);
	public void delete(Integer id);
	public List<BatteryCharge> findAllByCategory(String id);
}
