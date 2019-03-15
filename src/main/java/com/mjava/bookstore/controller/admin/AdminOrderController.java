package com.mjava.bookstore.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.service.OrderService;

@Controller
@RequestMapping(value="/admin/order")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 查询所有订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findAllOrders")
	public String findAllOrders(Model model) {
		List<Order> orderList = orderService.findAllOrders();
		model.addAttribute("orderList", orderList);
		model.addAttribute("orderState", "所有订单");
		return "adminjsps/admin/order/list";
	}
	
	/**
	 * 查询未付款订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findOrdersNotPaid")
	public String findOrdersNotPaid(Model model) {
		List<Order> orderList = orderService.findOrdersNotPaid();
		model.addAttribute("orderList", orderList);
		model.addAttribute("orderState", "未付款订单");
		return "adminjsps/admin/order/list";
	}
	
	/**
	 * 查询已付款订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findOrdersHavePaid")
	public String findOrdersHavePaid(Model model) {
		List<Order> orderList = orderService.findOrdersHavePaid();
		model.addAttribute("orderList", orderList);
		model.addAttribute("orderState", "已付款订单");
		return "adminjsps/admin/order/list";
	}
	
	/**
	 * 查询为收到订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findOrdersNotReceived")
	public String findOrdersNotReceived(Model model) {
		List<Order> orderList = orderService.findOrdersNotReceived();
		model.addAttribute("orderList", orderList);
		model.addAttribute("orderState", "未收货订单");
		return "adminjsps/admin/order/list";
	}
	
	/**
	 * 查询已完成订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findOrdersFinished")
	public String findOrdersFinished(Model model) {
		List<Order> orderList = orderService.findOrdersFinished();
		model.addAttribute("orderList", orderList);
		model.addAttribute("orderState", "已完成订单");
		return "adminjsps/admin/order/list";
	}
}
