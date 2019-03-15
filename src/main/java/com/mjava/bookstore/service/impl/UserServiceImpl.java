package com.mjava.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjava.bookstore.domain.User;
import com.mjava.bookstore.mapper.UserMapper;
import com.mjava.bookstore.service.UserService;
import com.mjava.bookstore.service.exception.UserException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User form) throws UserException {
		User user = userMapper.findByUsername(form.getUsername());
		
		//比较form与user的密码是否相同
		if(user==null) {
			throw new UserException("用户不存在");
		}
		if(!user.getPassword().equals(form.getPassword()) ) {
			throw new UserException("密码错误，请重新登录");
		}
		
		//查看用户的状态
		if(!user.isState()) {
			throw new UserException("您还未激活账号,请去邮箱激活");			
		}
		
		return userMapper.findByUsername(form.getUsername());
	}

	@Override
	public void regist(User form) throws UserException {
		//校验用户名是否被注册
		User user = userMapper.findByUsername(form.getUsername());
		if(user != null) {
			throw new UserException("用户名已被注册");
		}
		
		//校验Email
		user = userMapper.findByEmail(form.getEmail());
		if(user != null) {
			throw new UserException("邮箱已被使用");
		}
		
		userMapper.addUser(form);
		
	}

	@Override
	public void active(String code) throws UserException {
		
		User user = userMapper.findByCode(code);
		
		//如果用户不存在，说明激活码无效
		if(user == null) {
			throw new UserException("激活码无效");
		}
		
		//校验用户的状态是否为未激活状态，如果已激活，说明是二次激活抛出异常
		if(user.isState()) {
			throw new UserException("您已经激活过注册码了");
		}
		
		//修改用户状态为true
		userMapper.updateState(user.getUid(),true);
	}

	
}
