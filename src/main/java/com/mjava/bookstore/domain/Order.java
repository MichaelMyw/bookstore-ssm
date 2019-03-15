package com.mjava.bookstore.domain;

import java.util.Date;
import java.util.List;

public class Order {
	private String oid;//订单id
	private Date ordertime;//下单时间
	private double total;//总计
	private int state;//状态,1为未付款2为未发货3为未收货4为已完成
	private User owner;//所属订单用户
	private String address;//地址
	
	private List<OrderItem> orderItemList;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", owner="
				+ owner + ", address=" + address + ", orderItemList=" + orderItemList + "]";
	}


	
	
	
}
