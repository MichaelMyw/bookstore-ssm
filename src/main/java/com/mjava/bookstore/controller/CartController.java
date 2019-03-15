package com.mjava.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Book;
import com.mjava.bookstore.domain.Cart;
import com.mjava.bookstore.domain.CartItem;
import com.mjava.bookstore.service.BookService;

@Controller
@RequestMapping(value="/cart")
public class CartController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/addCartItem", method = { RequestMethod.POST, RequestMethod.GET })
	public String add(Model model, HttpServletRequest request, HttpSession session) {
		// 获取购物车
		Cart cart = (Cart) session.getAttribute("cart");

		System.out.println(cart);
		// 获取bid
		String bid = request.getParameter("bid");

		// 获取图书
		Book book = bookService.findBookByBid(bid);

		// 获取count
		int count = Integer.parseInt(request.getParameter("count"));

		System.out.println(book);
		System.out.println(count);

		// 创建购物车条目
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		System.out.println(cartItem);

		// 把条目塞进购物车中
		cart.add(cartItem);

		return "cart/list";
	}
	
	/**
	 * 删除购物车条目
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(Model model, HttpServletRequest request, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "cart/list";
	}
	
	
	/**
	 * 清空购物车
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String remove(Model model,HttpSession session) {
		Cart cart = (Cart)session.getAttribute("cart");
		cart.clear();
		return "cart/list";
	}
	
	

	
	

}
