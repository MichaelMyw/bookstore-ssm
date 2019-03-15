package com.mjava.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjava.bookstore.domain.Book;
import com.mjava.bookstore.mapper.BookMapper;
import com.mjava.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * 查找所有图书
	 */
	@Override
	public List<Book> findAllBook() {
		return bookMapper.findAllBook();
	}

	/**
	 * 根据类别查找图书
	 */
	@Override
	public List<Book> findBookByCategory(String cid) {
		return bookMapper.findBookByCategory(cid);
	}

	/**
	 * 根据图书id查找图书
	 */
	@Override
	public Book findBookByBid(String bid) {
		return bookMapper.findBookByBid(bid);
	}

	/**
	 * 更具图书id查找图书和类别
	 */
	@Override
	public Book findBookAndBookCategory(String bid) {
		return  bookMapper.findBookAndBookCategoryBybid(bid);
	}

	@Override
	public void add(Book book) {
		bookMapper.addBook(book);
	}
	


	@Override
	public void delete(String bid) {
		bookMapper.deleteBook(bid);
	}

	@Override
	public void edit(Book book) {
		bookMapper.editBook(book);
	}
	

}
