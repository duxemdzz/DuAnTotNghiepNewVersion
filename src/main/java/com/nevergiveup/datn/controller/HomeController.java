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
import com.nevergiveup.datn.entity.Cart;
import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.BrandService;
import com.nevergiveup.datn.service.CartService;
import com.nevergiveup.datn.service.CategoryService;
import com.nevergiveup.datn.service.ProductService;
import com.nevergiveup.datn.service.UsersService;
import com.nevergiveup.datn.service.WishListService;

@Controller

public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	WishListService wishLishService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	BrandService brandService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	UsersService userService;

	@RequestMapping("/")
	public String home(Model model) {
		return "users/home";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin(Model model) {

		String username = req.getRemoteUser();
		Users item = userService.findByUsername(username);
		Boolean flag = req.isUserInRole("ADMIN");
		String role = "ADMIN";
		if (flag) {
			model.addAttribute("quyen", role);
		} else {
			role = "STAF";
			model.addAttribute("quyen", role);
		}
		model.addAttribute("account", item);

		return "../static/assets/admin/layout_admin";
	}

	// sai chung
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

	@ModelAttribute("listAll")
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

	@ModelAttribute("listPdTablet")
	public List<Product> getProductsByCategoryTablet() {
		List<Product> list = productService.findByCategoryId("tablet");
		return list;
	}

	@ModelAttribute("listPdAccessory")
	public List<Product> getProductsByCategoryAcessory() {
		List<Product> list = productService.findByCategoryId("accessory");
		return list;
	}

	@ModelAttribute("listrandom")
	public List<Product> getProductRandom() {
		List<Product> list = productService.findByRandomProduct();
		return list;
	}
}
