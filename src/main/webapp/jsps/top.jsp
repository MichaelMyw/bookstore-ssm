
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
<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	/* background: #4682B4; */
	background: #E8E8E8
	/* 灰色 */
}

a {
	text-transform: none;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>

<body>
	<h1 style="text-align: center;">书店</h1>
	<div style="font-size: 10pt;">
		<c:choose>
			<c:when test="${empty sessionScope.session_user }">
				<br>
			
			
					<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent"><font
						color="black">登录</font></a>
			
					<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent"><font
						color="black">注册</font></a>
	

			</c:when>
			<c:otherwise>
			
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">您好：${sessionScope.session_user.username }</font>
				<ul class="nav nav-pills">

					<li><a href="cart/list.jsp"
						target="body">我的购物车</a>&nbsp;&nbsp;</li>
					<li><a href="${pageContext.request.contextPath}/order/myOrder"
						target="body">我的订单</a>&nbsp;&nbsp;</li>
						
					<li><a href="${pageContext.request.contextPath }/user/quit"
						target="_parent">退出</a></li>

				</ul>
			</c:otherwise>
		</c:choose>

	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
