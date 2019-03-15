package com.mjava.bookstore.service;

import com.mjava.bookstore.domain.User;
import com.mjava.bookstore.service.exception.UserException;

public interface UserService {
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public User login(User form) throws UserException;
	
	/**
	 * 注册功能
	 * @param form
	 * @throws UserException 
	 */
	public void regist(User form) throws UserException;
	
	
	/**
	 * 用户激活功能
	 * @param code
	 * @throws UserException
	 */
	public void active(String code) throws UserException;

}
