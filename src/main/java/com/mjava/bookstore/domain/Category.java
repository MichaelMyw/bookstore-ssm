package com.mjava.bookstore.domain;

/**
 * 类别实体类
 * @author Mayiwei
 *
 */
public class Category {
	String cid;//分类的id
	String cname;//分类的名字
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
}
