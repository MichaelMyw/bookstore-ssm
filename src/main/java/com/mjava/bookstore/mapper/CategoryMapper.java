package com.mjava.bookstore.mapper;

import java.util.List;

import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.service.exception.CategoryException;

public interface CategoryMapper {
	/**
	 * 查找所有类别
	 * @return
	 */
	public List<Category> findAllCategory();

	/**
	 * 添加分类
	 */
	public void addCategory(Category category);
	
	
	/**
	 * 删除分类
	 */
	public void deleteCategory(String cid) throws CategoryException;

	/**
	 * 根据cid查找分类
	 * @param cid
	 * @return
	 */
	public Category findCategoryByCid(String cid);

	/**
	 * 更新category
	 * @param category
	 */
	public void updateCategory(Category category);
}
