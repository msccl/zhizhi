<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/header.jsp"%>
<%@page import="com.qkzz.web.stat.bean.StatGame"%>
<%@ include file="../inc/nav.jsp"%>
<%
String ERR_MSG = (String)request.getAttribute("ERR_MSG");
if(ERR_MSG != null && !ERR_MSG.equals("")) {
	if(ERR_MSG.equals("PARA_WRONG")) { 
		%><div class="dotline0">
		参数传递错误，请返回!
		</div><%
	}
	%><%@ include file="/inc/footer.jsp"%><%
	return;
}

%>
		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="/developers/index">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="#">我的游戏</a></span>
				</div>

				<!--  -->
				<div class="clearfix">
					<div>
						请输入查询日期：
						<div style="margin:10px 10px 10px 0;">
							<form action="/stat/list/10/1" method="get">
							<span class="newgame">
							<input name="date" value="${date}"/><input type="submit" value="查询"/>
							</span>
							</form>
						</div>
					</div>
				
<%
String turnpage = (String)request.getAttribute("turnpage");
List<StatGame> gameList = (List<StatGame>)request.getAttribute("gameList");

if(gameList == null || gameList.size() == 0) {
	%><div>暂无游戏，请添加!</div><%
} else {
	%>
					<div class="gamelist">
						<table class="list_title"><tbody>
							<tr>
								<td class="it_a" style="width:20%;">游戏名称</td>
								<td class="it_c" style="width:20%;">总访问数</td>
								<td class="it_d" style="width:20%;">IP唯一数</td>
								<td class="it_e" style="width:20%;">用户唯一数</td>
								<td class="it_b" style="width:20%;">详细信息</td>
							</tr>
						</tbody></table>
	
	
	<%
	for(int i=0;i<gameList.size();i++) {
		StatGame bean = gameList.get(i);
		if(bean != null) {
			String className = "g_item";
			if((i+1)%2 == 0) {
				className = "g_item_bg";
			}			
			%>
						<table class="<%=className%>"><tbody>
							<tr>
								<td class="it_a" style="width:20%;"><%=bean.getGamename()%></td>
								<td class="it_c" style="width:20%;"><%=bean.getNum() %></td>
								<td class="it_d" style="width:20%;"><span class="gray14"><%=bean.getIp_num() %></span></td>
								<td class="it_e" style="width:20%;"><%=bean.getUserid_num() %></td>
								<td class="it_b" style="width:20%;"><a href="/stat/detail/10/1?code=<%=bean.getGamecode() %>">查看详细</a></td>
							</tr>
						</tbody></table>
			
			<%
		}
	}	
	%>
					</div>
					<div class="pagenav"><%=turnpage %></div><%
}
%>

				</div>

			</div>
			<!--  -->


		</div>



<%@ include file="../inc/footer.jsp"%>