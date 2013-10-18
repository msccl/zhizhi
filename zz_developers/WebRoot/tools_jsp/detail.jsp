<%@page import="com.qkzz.web.developer.bean.Tools"%>
<%@page import="com.qkzz.web.developer.bean.MoneyInfo"%>
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
		} else if(ERR_CODE.equals("TOOLS_NOT_EXIST")) {
			%><div class="dotline0">
			道具不存在或者已经被删除，请返回!
			</div><%
		} else if(ERR_CODE.equals("MONEYINFO_NOT_EXIST")) {
			%><div class="dotline0">
			道具使用的货币不存在或者已经被删除，请返回!
			</div><%
		}
		%><%@ include file="/inc/footer.jsp"%><%
		return;
	}

	Tools tools = (Tools)request.getAttribute("tools");
//	MoneyInfo moneyinfo = (MoneyInfo)request.getAttribute("moneyinfo");

	int status = tools.getStatus();
	String statusStr = "等待审核";
	if(status == 1) {
		statusStr = "审核通过";
	} else if(status == -1) {
		statusStr = "审核未通过";
	}
	%><table align="center" width="400">
		<tr>
			<td>道具名称</td><td><%=tools.getName() %></td>
		</tr>
		<tr>
			<td>道具图片</td><td><img src="<%=tools.getImg() %>" /></td>
		</tr>
		<tr>
			<td>道具描述</td><td><%=tools.getIntro() %></td>
		</tr>
		<tr>
			<td>是否可以交易</td><td><%=(tools.getCanexchange()==0)?"不可以":"可以" %></td>
		</tr>
		<tr>
			<td>是否可以拍卖</td><td><%=(tools.getCanauction()==0)?"不可以":"可以" %></td>
		</tr>
		<tr>
			<td>道具功能定义</td><td><%=tools.getFunctiondefine() %></td>
		</tr>
		<tr>
			<td>使用的货币</td><td><%//=moneyinfo.getName() %></td>
		</tr>
		<tr>
			<td>道具价格</td><td><%=tools.getPrice() %></td>
		</tr>
		<tr>
			<td>道具状态</td><td><%=statusStr %></td>
		</tr>
		<tr>
			<td>创建时间</td><td><%=tools.getCreatetime() %></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><a href="/tools/edit?gameid=<%=tools.getGameid() %>&id=<%=tools.getId()%>">编辑</a> <a href="#" onclick="history.back(1)">返回</a></td>
		</tr>
	</table>
<%@ include file="../inc/footer.jsp"%>