package com.mjava.bookstore.service;

import java.util.List;

import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.service.exception.OrderException;

public interface OrderService {

	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order);

	/**
	 * 查询我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrder(String uid);

	/**
	 * 加载订单 
	 * @param oid
	 * @return
	 */
	public Order loadOrder(String oid);

	/**
	 * 确认收货完成
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException;

	/**
	 * 查找所有订单
	 * @return
	 */
	public List<Order> findAllOrders();
	
	/**
	 * 查找未付款订单
	 * @return
	 */
	public List<Order> findOrdersNotPaid();
	
	/**
	 * 查找已付款订单
	 * @return
	 */
	public List<Order> findOrdersHavePaid();
	
	/**
	 * 查找未收到订单
	 * @return
	 */
	public List<Order> findOrdersNotReceived();
	
	/**
	 * 查找已完成订单
	 * @return
	 */
	public List<Order> findOrdersFinished();
	
}
