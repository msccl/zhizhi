<%@page import="com.qkzz.web.developer.bean.Tools"%>
<%@page import="com.qkzz.web.developer.bean.GameInfo"%>
<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/nav.jsp"%>
<%
	String ERR_CODE = (String)request.getAttribute("ERR_CODE");
	if(ERR_CODE != null && !ERR_CODE.equals("")) {
		if(ERR_CODE.equals("PARAM_MISS")) { 
			%><div class="dotline0">
			参数传递错误，请返回!
			</div><%
		} else if(ERR_CODE.equals("GAME_NOT_EXIST")) {
			%><div class="dotline0">
			游戏不存在或者已经被删除，请返回!
			</div><%
		}
		%><%@ include file="/inc/footer.jsp"%><%
		return;
	}

	GameInfo gi = (GameInfo)request.getAttribute("bean");
//	MoneyInfo moneyinfo = (MoneyInfo)request.getAttribute("moneyinfo");

	int status = gi.getStatus();
	String statusStr = "等待审核";
	if(status == 1) {
		statusStr = "审核通过";
	} else if(status == -1) {
		statusStr = "审核未通过";
	}
	%><table align="center" width="400">
		<tr>
			<td>游戏名称</td><td><%=gi.getName() %></td>
		</tr>
		<tr>
			<td>游戏图片</td><td><img src="<%=gi.getLogo() %>" /></td>
		</tr>
		<tr>
			<td>游戏描述</td><td><%=gi.getIntro() %></td>
		</tr>
		<tr>
			<td>游戏玩法</td><td><%=gi.getStrategy() %></td>
		</tr>
		<tr>
			<td>游戏地址</td><td><%=gi.getUrl() %></td>
		</tr>
		<tr>
			<td>游戏Host</td><td><%=gi.getHost() %></td>
		</tr>
		<tr>
			<td>游戏状态</td><td><%=statusStr %></td>
		</tr>
		<tr>
			<td>添加时间</td><td><%=gi.getCreatetime() %></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><a href="/game/edit?id=<%=gi.getId() %>">编辑</a> <a href="/game/del?id=<%=gi.getId() %>">删除</a> <a href="#" onclick="history.back(1)">返回</a></td>
		</tr>
	</table>
<%@ include file="../inc/footer.jsp"%>