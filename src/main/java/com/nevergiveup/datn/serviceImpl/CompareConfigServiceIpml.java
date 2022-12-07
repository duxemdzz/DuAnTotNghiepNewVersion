package com.nevergiveup.datn.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.nevergiveup.datn.entity.ProductConfigurationLaptop;
import com.nevergiveup.datn.entity.ProductConfigurationPhone;
import com.nevergiveup.datn.service.CompareConfigService;
import com.nevergiveup.datn.service.ConfigLaptopService;
import com.nevergiveup.datn.service.ConfigPhoneService;

@SessionScope
@Service("compareconfig")
public class CompareConfigServiceIpml implements CompareConfigService {
	Map<Integer, ProductConfigurationPhone> mapPhone = new HashMap<Integer, ProductConfigurationPhone>();

	Map<Integer, ProductConfigurationLaptop> mapLaptop = new HashMap<Integer, ProductConfigurationLaptop>();

	@Autowired
	ConfigPhoneService configPhoneService;
	@Autowired
	ConfigLaptopService configLaptopService;

	@Override
	public List<ProductConfigurationPhone> getProductConfigurationPhone() {
		ArrayList<ProductConfigurationPhone> dsPhone = new ArrayList<ProductConfigurationPhone>();
		for (Integer key : mapPhone.keySet()) {
			dsPhone.add(mapPhone.get(key));
		}
		return dsPhone;
	}

	@Override
	public List<ProductConfigurationLaptop> getProductConfigurationLaptop() {
		ArrayList<ProductConfigurationLaptop> dsLaptop = new ArrayList<ProductConfigurationLaptop>();
		for (Integer key : mapLaptop.keySet()) {
			dsLaptop.add(mapLaptop.get(key));
		}
		return dsLaptop;
	}

	@Override
	public void addPhone(int id) {
		ProductConfigurationPhone item = configPhoneService.findById(id);
		mapPhone.put(id, item);

	}

	@Override
	public void removePhone(int id) {
		mapPhone.remove(id);
	}

	@Override
	public void addLaptop(int id) {
		ProductConfigurationLaptop item = configLaptopService.findById(id);
		mapLaptop.put(id, item);

	}

	@Override
	public void removeLaptop(int id) {
		mapLaptop.remove(id);
	}
}
