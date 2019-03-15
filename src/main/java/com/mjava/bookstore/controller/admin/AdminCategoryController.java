package com.mjava.bookstore.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.service.CategoryService;
import com.mjava.bookstore.service.exception.CategoryException;
import com.mjava.bookstore.utils.CommonUtils;

@Controller
@RequestMapping(value="/admin/category")
public class AdminCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 查找所有分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findAllCategory",method = RequestMethod.GET)
	public String findAllCategory(Model model) {
		List<Category> categoryList = categoryService.findAllCategory();
		model.addAttribute("categoryList", categoryList);
		return "adminjsps/admin/category/list";
	}
	
	
	/**
	 * 添加分类
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addCategory",method = RequestMethod.POST)
	public String addCategory(Category category,Model model) {
		category.setCid(CommonUtils.uuid());
		categoryService.addCategory(category);
		return findAllCategory(model);
	}
	
	/**
	 * 删除分类
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteCategory",method= RequestMethod.GET)
	public String deleteCategory(Model model,HttpServletRequest request) {
		String cid = request.getParameter("cid");
		try {
			categoryService.deleteCategory(cid);
			
		} catch (CategoryException e) {
			model.addAttribute("msg",e.getMessage());
			return "adminjsps/msg";
		}
		
		return findAllCategory(model);
	}
	
	/**
	 * 跳转到修改分类页面 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editPre",method=RequestMethod.GET)
	public String editPre(Model model,HttpServletRequest request) {
		String cid = request.getParameter("cid");
		Category category = categoryService.findCategoryByCid(cid);
		model.addAttribute("category", category);
		return "adminjsps/admin/category/mod";
	
	}
	
	/**
	 * 修改分类
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(Model model,Category category) {
		categoryService.update(category);
		return findAllCategory(model);
	}

}
