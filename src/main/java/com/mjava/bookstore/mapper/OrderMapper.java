package com.mjava.bookstore.mapper;

import java.util.List;

import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.domain.OrderItem;

public interface OrderMapper {
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order);
	
	/**
	 * 添加订单条目
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList);

	/**
	 * 根据用户查询订单
	 * @param uid
	 * @return
	 */
	public List<Order> findOrdersByUid(String uid);

	/**
	 * 根据订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findOrderByOid(String oid);

	/**
	 * 查询订单的状态
	 * @param oid
	 * @return
	 */
	public int findStateByOid(String oid);

	/**
	 * 更新状态
	 * @param oid
	 * @param state
	 */
	public void updateStated(String oid, int state);

	/**
	 * 查找所有订单
	 * @return
	 */
	public List<Order> findAllOrders();

	/**
	 * 查找已完成订单
	 * @return
	 */
	public List<Order> findOrdersNotPaid();

	/**
	 * 查找未付款订单
	 * @return
	 */
	public List<Order> findOrdersHavePaid();

	/**
	 * 查找未收到订单
	 * @return
	 */
	public List<Order> findOrdersNotReceived();

	/**
	 * 查找未完成订单
	 * @return
	 */
	public List<Order> findOrdersFinished();
}
