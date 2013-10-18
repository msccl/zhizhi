<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/header.jsp"%>
<%@page import="com.qkzz.web.developer.bean.GameInfo"%>
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
						您当前已经有 0 款游戏，其中 0 款已经发布
						<div style="margin:10px 10px 10px 0;">
							<span class="newgame"><a href="/game/add"><img src="/images/newgame.gif"/></a></span>
						</div>
					</div>
<%

String turnpage = (String)request.getAttribute("turnpage");
List<GameInfo> gameList = (List<GameInfo>)request.getAttribute("gameList");

if(gameList == null || gameList.size() == 0) {
	%><div>暂无游戏，请添加!</div><%
} else {
	%>
					<div class="gamelist">
						<table class="list_title"><tbody>
							<tr>
								<td class="it_a"><a href="#">游戏图片</a></td>
								<td class="it_b"><a href="#">游戏名称</a></td>
								<td class="it_c"><a href="#">状态</a></td>
								<td class="it_d"><a href="#">游戏ID</a></td>
								<td class="it_e"><a href="#">相关操作</a></td>
							</tr>
						</tbody></table>
	
	
	<%
	for(int i=0;i<gameList.size();i++) {
		GameInfo bean = gameList.get(i);
		if(bean != null) {
			String className = "g_item";
			if((i+1)%2 == 0) {
				className = "g_item_bg";
			}
			int status = bean.getStatus();
			String statusStr = "<img src='/images/dot0.gif' title='等待审核'/>等待审核";
			if(status == 1) {
				statusStr = "<img src='/images/dot0.gif' title='通过'/>通过";
			} else if(status == -1) {
				statusStr = "<img src='/images/dot1.gif' title='未通过'/>未通过";
			}
			
			%>
						<table class="<%=className%>"><tbody>
							<tr>
								<td class="it_a"><img src="<%=bean.getLogo() %>" title="<%=bean.getName()%>"/></td>
								<td class="it_b"><%=bean.getName() %></td>
								<td class="it_c"><%=statusStr %></td>
								<td class="it_d"><span class="gray14"><%=bean.getGamecode() %></span></td>
								<td class="it_e"><a href="/game/edit?id=<%=bean.getId() %>">游戏资料</a> <a href="/tools/list/<%=bean.getId() %>/10/1">道具</a> <a href="/store/list/<%=bean.getId() %>/10/1">商店</a> <a href="/money/list/<%=bean.getId() %>/10/1">货币</a> <span class="del"><a href="/game/del?id=<%=bean.getId() %>">删除</a></span></td>
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