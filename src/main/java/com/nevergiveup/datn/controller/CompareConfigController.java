package com.nevergiveup.datn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.entity.ProductConfigurationLaptop;
import com.nevergiveup.datn.entity.ProductConfigurationPhone;
import com.nevergiveup.datn.service.CompareConfigService;
import com.nevergiveup.datn.service.ConfigLaptopService;
import com.nevergiveup.datn.service.ConfigPhoneService;
import com.nevergiveup.datn.service.ProductService;
import com.nevergiveup.datn.service.SessionService;

@Controller
public class CompareConfigController {

	@Autowired
	ConfigPhoneService configPhoneService;
	@Autowired
	ConfigLaptopService configLaptopService;
	@Autowired
	CompareConfigService compareConfigService;
	@Autowired
	ProductService productService;

	@RequestMapping("product/compare")
	public String compare(Model model) {
		model.addAttribute("phone", compareConfigService.getProductConfigurationPhone());
		model.addAttribute("laptop", compareConfigService.getProductConfigurationLaptop());
		return "users/products/productCompare/compare";
	}

// add config phone tablet
	@RequestMapping("/product/config/add/{id}")
	public String addCompare(Model model, @PathVariable("id") Integer id, RedirectAttributes params) {

		if (configPhoneService.existsById(id)) {
			if (compareConfigService.getProductConfigurationPhone().size() == 3) {
				params.addAttribute("message", "Chỉ so sánh tối đa 3 sản phẩm!");
				return "redirect:/";
			} else {
				compareConfigService.addPhone(id);
			}
		} else {
			params.addAttribute("message", "Chưa cập nhật được cấu hình!");
			return "redirect:/";
		}

		model.addAttribute("phone", compareConfigService.getProductConfigurationPhone());
		model.addAttribute("laptop", compareConfigService.getProductConfigurationLaptop());
		return "users/products/productCompare/compare";
	}

// delete phone tablet
	@RequestMapping("/product/config/delete/{id}")
	public String deleteCompare(@PathVariable("id") Integer id, RedirectAttributes params) {
		try {
			compareConfigService.removePhone(id);
			params.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			params.addAttribute("message", "Xóa thất bại!");
		}

		return "redirect:/product/compare";
	}

	// add laptop
	@RequestMapping("/product/config/add/laptop/{id}")
	public String addCompareLapTop(Model model, @PathVariable("id") Integer id, RedirectAttributes params) {
		if (configPhoneService.existsById(id)) {

			if (compareConfigService.getProductConfigurationPhone().size() == 3) {
				params.addAttribute("message", "Chỉ so sánh tối đa 3 sản phẩm!");
				return "redirect:/";
			} else {
				compareConfigService.addLaptop(id);
			}

		} else {
			params.addAttribute("message", "Chưa cập nhật được cấu hình!");
			return "redirect:/";
		}
		model.addAttribute("phone", compareConfigService.getProductConfigurationPhone());
		model.addAttribute("laptop", compareConfigService.getProductConfigurationLaptop());
		return "users/products/productCompare/compare";
	}

	// delete laptop
	@RequestMapping("/product/config/delete/laptop/{id}")
	public String deleteCompareLapTop(@PathVariable("id") Integer id, RedirectAttributes params) {
		try {
			compareConfigService.removeLaptop(id);
			params.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			params.addAttribute("message", "Xóa thất bại!");
		}

		return "redirect:/product/compare";
	}

	@ModelAttribute("listrandom")
	public List<Product> getProductRandom() {
		List<Product> list = productService.findByRandomProduct();
		return list;
	}
}
