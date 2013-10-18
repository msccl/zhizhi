<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.qkzz.user.service.ShortUrlService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>短地址测试</title>
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
  	<%
  	if(request.getParameter("submit") != null) {
  		String content = request.getParameter("content");
  		%><%=ShortUrlService.getShortContent(content) %><br/>
  		<br/><a href="short.jsp">返回</a><%
  		return;
  	}
  	%>
  
    <form action="short.jsp" method="post">
    	内容：<textarea rows="15" cols="40" name="content"></textarea><br/>
    	<input type="submit" name="submit" value="submit">
    </form>
    
    
  </body>
</html>