<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.qkban.alipay.*"%>
<%
	String tradeno = (String)request.getAttribute("indentnumber");
	String money = (String)request.getAttribute("money");
	String orderstatus = (String)request.getAttribute("orderstatus");
	
%><%@ include file='/common/header.jsp'%>
	<link rel="stylesheet" type="text/css" href="http://wangxing365.com/css/pay.main.ali.css">
	<div class="orderPayment">
		<div class="orderRes">
		<%
		if(orderstatus != null && orderstatus.equals("success")) {
			%><h1>7kban支付宝在线充值成功！</h1>
			<p>您为本次购买支付了总额<span style="color:red;font-weight:bold"><%=money %></span>元，请稍后查看您的资金账户确认是否到账</p>
			<div class="warning">
				<img src="http://wangxing365.com/img/warning.gif" /><b>说明：</b><br/>请牢记您本次的订单号<span style="color:red;font-weight:bold"><%=tradeno %></span>，便于7kban的快速处理！
			</div>
			<p>有任何问题，请随时联系我们：<b>service#7kban.com</b> 或给我们<a href="http://wangxing365.com/help/consult/0/1">提出反馈</a></p>
			<%
		} else {
			%><h1>7kban支付宝在线充值失败！</h1>
			<div class="warning">
				<img src="http://wangxing365.com/img/warning.gif" />请记住您的订单号<span style="color:red;font-weight:bold;"><%=tradeno %></span>与我们联系，避免您的损失！
			</div>
			<h1></h1>			
			<%
		}
		%>
			<h2><button class="payButton WordSeven" type="submit" onclick="window.location.href='/index'">返回首页继续充值</button></h2>	
		</div>
	</div>

<%@ include file='/common/footer.jsp'%>
