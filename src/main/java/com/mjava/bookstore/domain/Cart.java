package com.mjava.bookstore.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车实体类
 * @author Mayiwei
 *
 */
public class Cart {

	/**
	 * 创建Map存放条目，key为bid，值为book对应的订单条目
	 * 使用LinkedHashMap保证排序
	 */
	Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	
	/**
	 * 返回所有条目总价
	 * @return
	 */
	public double getTotal() {
		BigDecimal total = new BigDecimal(0);
		
		//遍历所有订单条目,map.values获取订单条目
		for (CartItem cartItem : map.values()) {
			
			//得到每个条目价格并求和
			BigDecimal subTotal = new BigDecimal(cartItem.getSubTotal()+"");
			total = subTotal.add(total);
		}
		
		return total.doubleValue();
	}
	
	/**
	 * 添加购物车条目
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		//如果订单条目本来就存在
		if(map.containsKey(cartItem.getBook().getBid())) {
			//1.拿到原条目
			CartItem _cartItem = map.get(cartItem.getBook().getBid());
			//2.条目数量合并 （原数量 + 新数量）
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			//3.将合并后的条目放进map
			map.put(cartItem.getBook().getBid(), _cartItem);

		}else {
			//如果订单条目不存在，则直接添加
			map.put(cartItem.getBook().getBid(), cartItem);	
		}
	}
	
	/**
	 * 清空购物车
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * 删除指定条目
	 * @param bid
	 */
	public void delete(String bid) {
		map.remove(bid);
	}
	
	/**
	 * 获取所有条目(我的购物车)
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
}
