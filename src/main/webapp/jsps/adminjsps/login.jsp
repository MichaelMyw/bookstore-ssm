<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Bootstrap -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

<h1 align="center">管理员登录页面</h1>
<hr/>
  <p style="font-weight: 900; color: red" align="center">${msg }</p>
  
  
      <!--  
            基础知识：  
            网格系统:通过行和列布局  
            行必须放在container内  
            手机用col-xs-*  
            平板用col-sm-*  
            笔记本或普通台式电脑用col-md-*  
            大型设备台式电脑用col-lg-*  
            为了兼容多个设备，可以用多个col-*-*来控制；  
        -->  
    <div class="container">  
       
        <div class="form row">  
            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form" action="${pageContext.request.contextPath }/admin/user/login" method="post" target="_top">   
            	<input type="hidden" name="method" value="login">
            
                <h3 class="form-title">Login to your account</h3>  
                <div class="col-sm-9 col-md-9">  
                    <div class="form-group">  
                        <i class="fa fa-user fa-lg"></i>  
                        <input class="form-control required" type="text" placeholder="Username" name="adminname" autofocus="autofocus"/>  
                    </div>  
                    <div class="form-group">  
                            <i class="fa fa-lock fa-lg"></i>  
                            <input class="form-control required" type="password" placeholder="Password" id="register_password" name="password"/>  
                    </div>  

                    <div class="form-group">  
                        <input type="submit" class="btn btn-success pull-middle" value="Login "/>  
                    </div>  
                </div>  
            </form>  
  
  
  
<%--   
<form action="<c:url value='/admin/AdminUserServlet'/>" method="post" target="_top">
	<input type="hidden" name="method" value="login">
	
	管理员账户：<input type="text" name="adminname" value=""/><br/>
	密　　　码：<input type="password" name="password"/><br/>
	<input type="submit" value="进入后台"/>
</form> --%>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
