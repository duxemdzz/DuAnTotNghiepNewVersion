package com.nevergiveup.datn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nevergiveup.datn.entity.Brand;
import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.entity.Product;
import com.nevergiveup.datn.entity.Review;
import com.nevergiveup.datn.entity.WishList;
import com.nevergiveup.datn.service.BrandService;
import com.nevergiveup.datn.service.CategoryService;
import com.nevergiveup.datn.service.ProductService;
import com.nevergiveup.datn.service.ReviewService;
import com.nevergiveup.datn.service.SessionService;
import com.nevergiveup.datn.service.WishListService;

@Controller
public class ProductController {
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
	SessionService session;
	@Autowired
	ReviewService reviewService;

	@RequestMapping("/product/page")
	public String ProductPage(Model model, @RequestParam("category") Optional<String> cid,
			@RequestParam("p") Optional<Integer> p, @RequestParam("keywords") Optional<String> kw,
			@RequestParam("field") Optional<String> field,
			@RequestParam("brand") Optional<String> brand,
			@RequestParam("price") Optional<Double> price) {
		Sort sort = Sort.by(Direction.ASC, field.orElse("id"));
		String cateid = req.getParameter("category");
		if (cateid != null) {
			model.addAttribute("cate", "category=" + cateid + "&");
		}
		String fieldSearch = req.getParameter("field");
		if (fieldSearch != null) {
			model.addAttribute("field", "field=" + fieldSearch + "&");
		}
		String keywords = req.getParameter("keywords");
		if (keywords != null) {
			model.addAttribute("keywords", "keywords=" + keywords + "&");
		}int pa = p.orElse(0);
		if (pa < 0) {
			pa = 0;
		}
		String kwords = kw.orElse(session.get("keywords", ""));
		session.set("keywords", kwords);
		Pageable pageable = PageRequest.of(pa, 8, sort);
		try {
			Page<Product> page = productService.findByKeyword("%" + kwords + "%", pageable);
			int lastPage = page.getTotalPages();
			int prePage = page.getNumber();
			if (prePage + 1 > lastPage) {
				page = productService.findByKeyword("%" + kwords + "%", PageRequest.of(lastPage - 1, 8));
			}
			model.addAttribute("page", page);
			if (cid.isPresent()) {
				Page<Product> list = productService.findByCategoryId(cid.get(), pageable);
				model.addAttribute("page", list);
				System.err.println("1");
			}
		} catch (Exception e) {
			Page<Product> page = productService.findByKeyword("%%", pageable);
			model.addAttribute("page", page);
			System.err.println("2");
			System.err.println(e);
			
		}

		return "users/products/productPage/page";
	}

	@RequestMapping("/product/wishlist")
	public String wishlist() {
		return "users/products/wishList/wishlist";
	}

	@RequestMapping("product/productdetail/{id}")
	public String ProductDetail(@PathVariable("id") Integer id, Model model) {
		try {
			Product product = productService.findById(id);
			model.addAttribute("pd", product);
			List<Product> listpd = productService.findByCategoryId(product.getCategory().getId());
			List<Review> listRv = reviewService.findReviewByProductId(product.getId());
			model.addAttribute("listReview", listRv);
			model.addAttribute("listpdCorrelative", listpd);
		} catch (Exception e) {
			System.err.println(e);
			return "users/subpage/404";
		}

		return "users/products/productDetail/productdetail";
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

	@ModelAttribute("listrandom")
	public List<Product> getProductRandom() {
		List<Product> list = productService.findByRandomProduct();
		return list;
	}

	@ModelAttribute("priceListHashCode")
	public Map<Integer, String> getGenders() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1000000, "Dưới 1 triêu");
		map.put(2000000, "Dưới 2 triêu");
		map.put(5000000, "Dưới 5 triêu");
		map.put(7000000, "Dưới 7 triêu");
		map.put(10000000, "Dưới 10 triêu");
		map.put(20000000, "Dưới 20 triêu");
		map.put(100000000, "Trên 20 triêu");
		return map;
	}

}
