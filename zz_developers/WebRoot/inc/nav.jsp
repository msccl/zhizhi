<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.qkzz.web.developer.bean.Developers"%>
<%
Developers user = (Developers)request.getAttribute("user");
int navid = 1;
try {
	navid = (Integer)request.getAttribute("navid");
} catch(Exception e) {
	navid = 1;
}
%>
		<div class="header">
			<div class="top"></div>
			<div class="nav">
				<ul class="clearfix">
					<li class="<%=(navid==1)?"bluebt":"graybt" %>" style="width:75px;">
						<div class="bt_l" style="width:40px;"><a href="/developers/index">首页</a></div>
						<div class="bt_r"></div>
					</li>
					<li class="<%=(navid==2)?"bluebt":"graybt" %>">
						<div class="bt_l"><a href="/game/list/10/1">我的游戏</a></div>
						<div class="bt_r"></div>
					</li>
					<li class="<%=(navid==3)?"bluebt":"graybt" %>">
						<div class="bt_l"><a href="/stat/list/10/1">数据统计</a></div>
						<div class="bt_r"></div>
					</li>
					<li class="<%=(navid==4)?"bluebt":"graybt" %>">
						<div class="bt_l"><a href="/developers/detail">帐户信息</a></div>
						<div class="bt_r"></div>
					</li>

					<%
					if(user != null) {
					%>
					<li style="float:right;">
						<div class="bt3">
							<div class="bt_l"><a href="/developers/logout">退  出</a></div>
							<div class="bt_r"></div>
						</div>
					</li>
					<li class="logineduser" style="float:right;">
						<span><b><%=user.getName() %></b></span>
					</li>
					<%
					}
					%>
				</ul>
			</div>
			<div class="navline"></div>
			<div class="search">
				<ul class="clearfix">
					<form action="/game/search" method="get" name="searchForm">
					<li class="left"><input type="text" value="输入游戏名称" name="q" id="s"> </li>
					<li class="left">
						<div class="bt2">
							<div class="bt_l"><a href="#" onclick="document.searchForm.submit()">搜  索</a></div>
							<div class="bt_r"></div>
						</div>
					</li>
					</form>
				</ul>
			</div>
		</div>
