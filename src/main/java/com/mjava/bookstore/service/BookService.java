package com.mjava.bookstore.service;

import java.util.List;

import com.mjava.bookstore.domain.Book;

public interface BookService {
	
	/**
	 * 查找所有图书
	 * @return
	 */
	public List<Book> findAllBook();

	/**
	 * 根据类别查找图书
	 * @param cid
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
	 * 查找图书和图书类别
	 * @param bid
	 * @return
	 */
	public Book findBookAndBookCategory(String bid);

	/**
	 * 添加图书
	 * @param book
	 */
	public void add(Book book);

	/**
	 * 更新图书
	 * @param book
	 */
	public void edit(Book book);

	/**
	 * 删除图书
	 * @param bid
	 */
	public void delete(String bid);
}
