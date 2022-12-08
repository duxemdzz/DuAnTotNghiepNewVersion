package com.nevergiveup.datn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.service.CategoryService;

@Controller
public class SubPageController {
	@Autowired
	CategoryService categoryService;

	@RequestMapping("/about")
	public String about() {
		return "users/subpage/about";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "users/subpage/contact";
	}

	@RequestMapping("/faq")
	public String faq() {
		return "users/subpage/faq";
	}

	@RequestMapping("/security")
	public String security() {
		return "users/subpage/security";
	}

	@ModelAttribute("listCate")
	public List<Category> getCategoryAll() {
		List<Category> list = categoryService.findAll();
		return list;
	}
}
