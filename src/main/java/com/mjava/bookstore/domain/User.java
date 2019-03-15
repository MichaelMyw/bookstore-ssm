package com.mjava.bookstore.domain;

/**
 * 用户实体类
 * @author Mayiwei
 *
 */
public class User {
	
	private String uid;//用户的uuid
	private String username;//用户名
	private String password;//密码
	private String email;//邮箱
	private String code;//激活码
	private boolean state;//激活码的状态
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", code="
				+ code + ", state=" + state + "]";
	}
	
	
	
}
