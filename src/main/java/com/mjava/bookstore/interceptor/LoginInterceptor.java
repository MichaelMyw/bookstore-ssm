package com.mjava.bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mjava.bookstore.domain.User;

public class LoginInterceptor implements HandlerInterceptor {

	//进入handler方法之前执行
	//身份认证和身份授权，如果认证不通过表示当前用户没有登陆舰，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		User user = (User)request.getSession().getAttribute("session_user");
		if(user != null) {
			return true;
		}else {
			request.setAttribute("msg", "你还没有登录");
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
		
		return false;
	}

	//进行handler方法之后，返回modelandview之前执行
	//应用场景从modelandview出发，将共用的模型数据(比如菜单导航)从这里传到视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//执行handler完成执行此方法
	//应用场景：统一异常处理，日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
