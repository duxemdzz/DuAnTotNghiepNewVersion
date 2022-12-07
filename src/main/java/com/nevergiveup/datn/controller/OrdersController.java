package com.nevergiveup.datn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevergiveup.datn.entity.Brand;
import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.entity.Order;
import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.BrandService;
import com.nevergiveup.datn.service.CategoryService;
import com.nevergiveup.datn.service.OrderService;
import com.nevergiveup.datn.service.ProductService;
import com.nevergiveup.datn.service.WishListService;

@Controller
public class OrdersController {
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishLishService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	BrandService brandService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/order/history")
	public String order(Model model) {
		try {
			String username = request.getRemoteUser();
			model.addAttribute("orders", orderService.findByUsername(username));
		} catch (Exception e) {
			return "users/subpage/404";
		}
		
		return "/users/order/history-order";
	}
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		try {
			Order od = orderService.findById(id);
			model.addAttribute("order", od.getTotal());
			model.addAttribute("order", orderService.findById(id));
			
		} catch (Exception e) {
			return "users/subpage/404";
		}
		return "/users/order/order-detail";
		
	}
	// sai tum lum la
	@ModelAttribute("listCate")
	public List<Category> getCategoryAll() {
		List<Category> list = categoryService.findAll();
		return list;
	}

	@ModelAttribute("listBrands")
	public List<Brand> getBrandsAll() {
		List<Brand> list = brandService.findAll();
		return list;
	}

	@ModelAttribute("listpd2")
	public List<Product> getProductsAll() {
		List<Product> list = productService.findAll();
		return list;
	}
	@ModelAttribute("listPdMobile")
	public List<Product> getProductsByCategoryMobile() {
		List<Product> list = productService.findByCategoryId("mobile");
		return list;
	}
	@ModelAttribute("listPdLaptop")
	public List<Product> getProductsByCategoryLaptop() {
		List<Product> list = productService.findByCategoryId("laptop");
		return list;
	}
}
