package com.mjava.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.mapper.BookMapper;
import com.mjava.bookstore.mapper.CategoryMapper;
import com.mjava.bookstore.service.CategoryService;
import com.mjava.bookstore.service.exception.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<Category> findAllCategory() {
		
		return categoryMapper.findAllCategory();
	}

	@Override
	public void addCategory(Category category){
		categoryMapper.addCategory(category);
	}

	@Override
	public void deleteCategory(String cid) throws CategoryException{
		int count = bookMapper.getCountByCid(cid);
		
		if(count>0) {
			throw new CategoryException("该分类下还有图书，不能删除");
		}
		categoryMapper.deleteCategory(cid);
			
	}

	@Override
	public Category findCategoryByCid(String cid) {
		return categoryMapper.findCategoryByCid(cid);
	}

	@Override
	public void update(Category category) {
		categoryMapper.updateCategory(category);
		
	}

}
