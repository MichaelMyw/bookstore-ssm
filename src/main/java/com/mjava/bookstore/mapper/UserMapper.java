package com.mjava.bookstore.mapper;

import com.mjava.bookstore.domain.User;


public interface UserMapper {
		
	 //根据用户名查找
	public User findByUsername(String username); 
	
	
	//根据email查找
	public User findByEmail(String email);
	
	//添加用户
	public void addUser(User user);
	
	//根据激活码查找用户
	public User findByCode(String code);
	
	//更新状态码
	public void updateState(String uid,boolean state);
      
	 
}
