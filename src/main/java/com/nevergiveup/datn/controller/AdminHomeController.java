package com.nevergiveup.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
	@RequestMapping
	public String AdminHome() {
		return"";
	}
}
