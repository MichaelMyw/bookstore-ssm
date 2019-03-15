package com.mjava.bookstore.domain;

import java.math.BigDecimal;

/**
 * 购物车条目实体类
 * @author Mayiwei
 *
 */
public class CartItem {

	private Book book;//图书
	private int count;//数量

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 小计
	 * 使用bigDecimal获取更准确的精度
	 * @return
	 */
	public double getSubTotal() {
		
		BigDecimal bookPrice = new BigDecimal(book.getPrice()+"");
		BigDecimal bookCount = new BigDecimal(count + "");
		
		BigDecimal total = bookPrice.multiply(bookCount);
				
		return total.doubleValue();
	}
	
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
}
