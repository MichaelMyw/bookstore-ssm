package com.mjava.bookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mjava.bookstore.domain.Cart;
import com.mjava.bookstore.domain.CartItem;
import com.mjava.bookstore.domain.Order;
import com.mjava.bookstore.domain.OrderItem;
import com.mjava.bookstore.domain.User;
import com.mjava.bookstore.service.OrderService;
import com.mjava.bookstore.service.exception.OrderException;
import com.mjava.bookstore.utils.CommonUtils;
import com.mjava.bookstore.utils.PaymentUtil;


@Controller
@RequestMapping(value="/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 生成订单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addOrder", method = RequestMethod.GET)
	public String addOrder(Model model, HttpSession session) {

		Cart cart = (Cart) session.getAttribute("cart");
		// 生成order对象
		Order order = new Order();
		order.setOid(CommonUtils.uuid());// 设置订单id
		order.setOrdertime(new Date());// 下单时间
		order.setState(1);// 设置为1表示未付款
		User user = (User) session.getAttribute("session_user");
		order.setOwner(user);// 设置订单所有者
		order.setTotal(cart.getTotal());// 设置订单小计

		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		// 循环遍历Cart中所有cartItem 使用每一个CartItem对象创建OrderItem对象，并添加到集合中
		for (CartItem cartItem : cart.getCartItems()) {

			OrderItem orderItem = new OrderItem();
			orderItem.setIid(CommonUtils.uuid());
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setOrder(order);
			orderItem.setBook(cartItem.getBook());
			orderItemList.add(orderItem);
		}

		order.setOrderItemList(orderItemList);// 设置订单条目列表

		// 清空购物车
		cart.clear();

		// 调用service方法生成订单
		orderService.addOrder(order);

		model.addAttribute("order", order);

		return "order/desc";
	}

	/**
	 * 我的订单
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/myOrder",method=RequestMethod.GET)
	public String myOrder(Model model,HttpSession session) {
		User user = (User)session.getAttribute("session_user");
		String uid = user.getUid();
		List<Order> orderList = orderService.myOrder(uid);
		model.addAttribute("orderList",orderList);
		return "order/list";
	}
	
	/**
	 * 加载订单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadOrder",method = RequestMethod.GET)
	public String loadOrder(Model model,HttpServletRequest request) {
		String oid = request.getParameter("oid");
		Order order = orderService.loadOrder(oid);
		model.addAttribute("order",order);
		return "order/desc";
	}
	
	
	/**
	 * 确认收货
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/confirm",method= RequestMethod.GET)
	public String confirm(Model model,HttpServletRequest request) {
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			model.addAttribute("msg", "恭喜，交易成功");
		} catch (OrderException e) {
			model.addAttribute("msg",e.getMessage());
		}
		return "msg";
	}
	
	/**
	 * 去支付
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/pay",method= RequestMethod.POST)
	public String pay(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		/*
		 * 准备13参数 ,先读取配置文件获取id和keyvalue
		 */
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(in);

		String p0_Cmd = "Buy";
		String p1_MerId = props.getProperty("p1_MerId");
		String p2_Order = request.getParameter("oid");
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = props.getProperty("p8_Url");
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";

		// 计算hmac
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		// 连接易宝的网址和13+1个参数
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);

		System.out.println(url);
		response.sendRedirect(url.toString());
		return null;

	}
	
}
