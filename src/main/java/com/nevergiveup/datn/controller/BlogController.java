package com.nevergiveup.datn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevergiveup.datn.entity.Category;
import com.nevergiveup.datn.entity.News;
import com.nevergiveup.datn.service.CategoryService;
import com.nevergiveup.datn.service.NewService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	NewService newService;
	@Autowired
	CategoryService categoryService;
	@RequestMapping("/list")
	public String showBlogArchive(Model model) {
		model.addAttribute("blogList", newService.findAll());
		return "users/blog/blog-archive";
	}

	@RequestMapping("/detail/{id}")
	public String showBlogSingle(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("blog", newService.findById(id));
		return "users/blog/blog-single";
		
	}

	@RequestMapping("/add")
	public String addBlog() {
		return "";
	}

	@RequestMapping("/update")
	public String updateBlog() {
		return "";
	}

	@RequestMapping("/delete")
	public String deletelog() {
		return "";
	}
	@ModelAttribute("listCate")
	public List<Category> getCategoryAll() {
		List<Category> list = categoryService.findAll();
		return list;
	}
	@ModelAttribute("blogList1")
	public List<News> getBlogAll() {
		List<News> list = newService.findAll();
		return list;
	}
}
