package com.mjava.bookstore.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mjava.bookstore.domain.Cart;
import com.mjava.bookstore.domain.User;
import com.mjava.bookstore.service.UserService;
import com.mjava.bookstore.service.exception.UserException;
import com.mjava.bookstore.utils.CommonUtils;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User form, Model model, HttpSession session,HttpServletRequest request) {

		// 输入校验,把错误信息保存到一个map中,键为表单字段名，值为错误信息
		Map<String, String> errors = new HashMap<String, String>();
		// 2. 获取form中的username, password,email并校验
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if (username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名必须在3到10位之间");
		}

		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if (password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码必须在3到10位之间");
		}
		// 3. 判断是否存在错误信息
		if (errors.size() > 0) {
			// 保存错误信息，form数据到request域，转发到regist.jsp
			model.addAttribute("errors", errors);
			model.addAttribute("form", form);
			return "user/login";
		}

		try {
			User user = userService.login(form);
			session.setAttribute("session_user", user);
			
			//给用户添加一辆购物车，即在session中保存session对象
			session.setAttribute("cart", new Cart());

			
			return "main";
		} catch (UserException e) {
			// 如果发送异常，则显示错误信息到login.jsp
			model.addAttribute("msg", e.getMessage());
			return "user/login";
		}
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(User form,Model model) throws IOException {

		// 补全uuid和code
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());

		// 输入校验,把错误信息保存到一个map中,键为表单字段名，值为错误信息
		Map<String, String> errors = new HashMap<String, String>();
		// 2. 获取form中的username, password,email并校验
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if (username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名必须在3到10位之间");
		}

		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if (password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码必须在3到10位之间");
		}
		
		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "email不能为空");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "email格式错误");
		}
		// 3. 判断是否存在错误信息
		if (errors.size() > 0) {
			// 保存错误信息，form数据到request域，转发到regist.jsp
			model.addAttribute("errors", errors);
			model.addAttribute("form", form);
			return "user/regist";
		}
		
		
		try {
			//调用service方法完成注册
			userService.regist(form);
		} catch (UserException e) {
			//如果出错，保存错误信息完成回显
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("form",form);
			return "user/regist";
		}
		//发邮件
		sendEmail(form);
		model.addAttribute("msg","注册成功！请马上到邮箱激活");
		return "msg";

	}
	
	/**
	 * 用户激活
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/active", method=RequestMethod.GET)
	public String active(Model model, HttpServletRequest request) {
		String code = request.getParameter("code");
		
		try {
			userService.active(code);
			model.addAttribute("msg", "激活成功");
			return "msg";
			
		} catch (UserException e) {
			model.addAttribute("msg", e.getMessage());
			return "msg";
		}
	}
	
	@RequestMapping(value="/quit",method=RequestMethod.GET)
	public String quit(Model model,HttpSession session) {
		session.invalidate();
		return "main";
	}

	/**
	 * 发邮件
	 * @param form
	 * @throws IOException
	 */
	private void sendEmail(User form) throws IOException {
		// 获取配置文件
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");// 获取主机
		String uname = props.getProperty("uname");// 获取用户名
		String pwd = props.getProperty("pwd");// 获取密码
		String from = props.getProperty("from");// 获取发件人
		String subject = props.getProperty("subject");// 获取主题
		String content = props.getProperty("content");// 获取内容
		String to = form.getEmail();// 获取收件人

		// content是{0}占位符， 后面的参数替换站位符，有几个占位符就有几个参数
		content = MessageFormat.format(content, form.getCode());// 替换占位符
		
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);// 指定主机
		prop.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(uname, pwd);
			}
		};
		Session session = Session.getDefaultInstance(prop, auth);
		MimeMessage msg = new MimeMessage(session);// 创建邮件对象
		try {
			//设置发件人
			msg.setFrom(new InternetAddress(from));
			//设置收件人
			msg.addRecipients(RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setContent(content,"text/html;charset=utf-8");
			
			Transport.send(msg);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
