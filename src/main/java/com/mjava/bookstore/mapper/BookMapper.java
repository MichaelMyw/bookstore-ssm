package com.mjava.bookstore.mapper;

import java.util.List;

import com.mjava.bookstore.domain.Book;

public interface BookMapper {
	
	/**
	 * 查找所有图书
	 * @return
	 */
	public List<Book> findAllBook();

	/**
	 * 根据分类查找图书
	 * @return
	 */
	public List<Book> findBookByCategory(String cid);

	/**
	 * 根据bid查找图书
	 * @param bid
	 * @return
	 */
	public Book findBookByBid(String bid);
	
	/**
	 * 根据图书类别获取图书本书
	 * @param cid
	 * @return
	 */
	public int getCountByCid(String cid);
	
	
	/**
	 * 根据bid查询图书，并能查出具体的category(需要多表查询)
	 * @param bid
	 * @return
	 */
	public Book findBookAndBookCategoryBybid(String bid);

	
	/**
	 * 添加图书
	 * @param book
	 */
	public void addBook(Book book);

	/**
	 * 编辑图书
	 * @param book
	 */
	public void editBook(Book book);

	/**
	 * 删除图书
	 * @param bid
	 */
	public void deleteBook(String bid);
	
}
