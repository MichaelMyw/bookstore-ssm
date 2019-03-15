<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<!-- 
  	1. 显示errors，
  	2. 显示异常错误
  	3. 回显
   -->
	<h1 align="center">注册</h1>
	<p style="color: red; font-weight: 900">${msg }</p>

	<div class="form row col-sm-offset-4 col-md-offset-4 col-sm-4 col-md-4">
		<form class="form-horizontal  " id="register_form"
			action="${pageContext.request.contextPath}/user/regist" method="post" target="_top">
			<input type="hidden" name="method" value="regist" />

			<h3 class="form-title">Register</h3>
			<div class="">
				<div class="form-group">
					<i class="fa fa-user fa-lg"></i> <input
						class="form-control required" type="text" placeholder="Username"
						name="username" autofocus="autofocus" value="${form.username }" />
					<span style="color: red; font-weight: 900">${errors.username }</span>

				</div>
				<div class="form-group">
					<i class="fa fa-lock fa-lg"></i> <input
						class="form-control required" type="password"
						placeholder="Password" id="register_password" name="password"
						value="${form.password }" /> <span
						style="color: red; font-weight: 900">${errors.password }</span>
				</div>

				<div class="form-group">
					<i class="fa fa-lock fa-lg"></i> <input
						class="form-control required" type="text" placeholder="Email"
						id="register_email" name="email" value="${form.email }" /> <span
						style="color: red; font-weight: 900">${errors.email }</span>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success pull-middle"
						value="Sign Up " />
				</div>
			</div>
		</form>
	</div>

	<!--   <h1>注册</h1> -->
	<%-- <form action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" value="${form.username }"/>
	<span style="color: red; font-weight: 900">${errors.username }</span>
	<br/>
	密　码：<input type="password" name="password" value="${form.password }"/>
	<span style="color: red; font-weight: 900">${errors.password }</span>
	<br/>
	邮　箱：<input type="text" name="email" value="${form.email }"/>
	<span style="color: red; font-weight: 900">${errors.email }</span>
	<br/>
	<input type="submit" value="注册"/>	
</form> --%>
</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</html>
