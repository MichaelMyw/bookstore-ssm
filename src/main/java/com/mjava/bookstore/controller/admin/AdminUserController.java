package com.mjava.bookstore.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin/user")
public class AdminUserController {
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,Model model) {
		String adminname = request.getParameter("adminname");
		String password = request.getParameter("password");
		
		
		if(adminname.equals("admin") & password.equals("123456")) {
			request.getSession().setAttribute("adminname", adminname);
			return "adminjsps/admin/index";
			
		}else {
			model.addAttribute("msg","登录失败，请使用管理员账号进行登录");
			return "adminjsps/login";
		}
	}

}
