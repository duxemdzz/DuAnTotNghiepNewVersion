package com.nevergiveup.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart/detail")
	public String cart() {
		return "users/carts/cart/cart";
	}

	@RequestMapping("/cart/checkout")
	public String checkout() {
		return "users/carts/checkout/checkout";
	}

}
