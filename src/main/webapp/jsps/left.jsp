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

<title>left</title>
<base target="body" />
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
* {
	font-size: 10pt;
	text-align: center;
}

/* div {
	background: #87CEFA;
	margin: 3px;
	padding: 3px;
} */

a {
	text-decoration: none;
}
</style>
</head>



<body>
<ul  class="list-group">

	<li class="list-group-item">
		<a href="${pageContext.request.contextPath }/book/showAllBook">全部分类</a>
	</li>

	<c:forEach items="${categoryList }" var="category">
		<li class="list-group-item">
			<a href="${pageContext.request.contextPath }/book/showBookByCategory?cid=${category.cid }">${category.cname }</a>
		</li>
	</c:forEach>
</ul >

   <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
