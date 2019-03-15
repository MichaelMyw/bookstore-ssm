package com.mjava.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.service.CategoryService;
@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/showAllCategory",method = RequestMethod.GET)
	public String showAllCategory(Model model) {
		List<Category> categoryList = categoryService.findAllCategory();
		
		model.addAttribute("categoryList",categoryList);
		return "left";
	}
}
