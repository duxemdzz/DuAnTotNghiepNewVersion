package com.nevergiveup.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubPageController {
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
}
