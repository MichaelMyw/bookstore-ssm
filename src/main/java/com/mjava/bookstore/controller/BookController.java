package com.mjava.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Book;
import com.mjava.bookstore.service.BookService;

@Controller
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 展示所有图书
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showAllBook",method=RequestMethod.GET)
	public String showAllBook(Model model) {
		List<Book> bookList = bookService.findAllBook();
		model.addAttribute("bookList", bookList);
		return "book/list";	
	}
	
	/**
	 * 根据类别展示图书
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showBookByCategory",method=RequestMethod.GET)
	public String showBookByCategory(Model model, HttpServletRequest request) {
		
		String cid = request.getParameter("cid");
		List<Book> bookList = bookService.findBookByCategory(cid);		
		model.addAttribute("bookList", bookList);	
		return "book/list";
	}
	
	
	/**
	 * 加载图书
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadBook",method = RequestMethod.GET)
	public String loadBook(Model model, HttpServletRequest request) {
		String bid = request.getParameter("bid");
		Book book = bookService.findBookByBid(bid);
		model.addAttribute("book", book);
		return "book/desc";
	}
	

}
