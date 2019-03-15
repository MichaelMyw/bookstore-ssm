package com.majava.bookstore.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mjava.bookstore.domain.Book;
import com.mjava.bookstore.domain.Category;
import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.domain.User;
import com.mjava.bookstore.mapper.BookMapper;
import com.mjava.bookstore.mapper.CategoryMapper;
import com.mjava.bookstore.mapper.OrderMapper;
import com.mjava.bookstore.mapper.UserMapper;
import com.mjava.bookstore.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryMapper categoryMapper;


	@Test
	public void testfindByUsername() throws Exception {
		System.out.println(userMapper);
		User user = userMapper.findByUsername("mayiwei");
		System.out.println(user);
	}

	@Test
	public void testfindOrdersByUid() {
		List<Order> orderList = orderMapper.findOrdersByUid("8C8882FA300F4CF3931B61BE764D4DE0");
		for (Order order : orderList) {
			System.out.println(order);
		}
	}

	@Test
	public void testfindOrdersByOid() {
		Order order = orderMapper.findOrderByOid("AC0D2DD8B5F44A6FAF6724A92FCBAA79");
		System.out.println(order);	
	}
	
	
	@Test
	public void testUpdateBook() {
		Book book = bookMapper.findBookByBid("158A22EF24B74775BC8971A3F2D8CECB");
		book.setBname("vvvv");
		Category c = categoryMapper.findCategoryByCid("2");
		book.setCategory(c);
		bookService.edit(book);
		System.out.println(book);	
	}
}
