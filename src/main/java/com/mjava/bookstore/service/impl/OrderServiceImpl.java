package com.mjava.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.mapper.OrderMapper;
import com.mjava.bookstore.service.OrderService;
import com.mjava.bookstore.service.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	@Transactional
	public void addOrder(Order order) {
		orderMapper.addOrder(order);
		orderMapper.addOrderItemList(order.getOrderItemList());
	}

	@Override
	public List<Order> myOrder(String uid) {
		return orderMapper.findOrdersByUid(uid);
	}

	@Override
	public Order loadOrder(String oid) {
		return orderMapper.findOrderByOid(oid);
	}

	@Override
	public void confirm(String oid) throws OrderException {
		
		int state = orderMapper.findStateByOid(oid);
		
		//如果状态为不是3 则抛出异常
		if(state!=3) {
			throw new OrderException("订单确认失败");
		}
		// 修改订单状态为4
		orderMapper.updateStated(oid, 4);
		
	}

	@Override
	public List<Order> findAllOrders() {
		return orderMapper.findAllOrders();
	}

	@Override
	public List<Order> findOrdersNotPaid() {
		return orderMapper.findOrdersNotPaid();
	}

	@Override
	public List<Order> findOrdersHavePaid() {
		return orderMapper.findOrdersHavePaid();
	}

	@Override
	public List<Order> findOrdersNotReceived() {
		return orderMapper.findOrdersNotReceived();
	}

	@Override
	public List<Order> findOrdersFinished() {
		return orderMapper.findOrdersFinished();
	}
	
	

}
