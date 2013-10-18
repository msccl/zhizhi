<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.qkzz.web.developer.bean.MoneyInfo"%>
<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/nav.jsp"%>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<%
MoneyInfo mi = (MoneyInfo)request.getAttribute("moneyinfobean");
%>
<form action="/money/edit" method="post">
	<input type="hidden" name="id" value="<%=mi.getId() %>" />
    <input type="hidden" name="gameid" value="<%=mi.getGameid() %>" />
    <table cellpadding=0 cellspacing=0>
        <tr>
            <td style="height:30px;">货币名称：</td><td><input type="text" size="31" name="name" value="<%=mi.getName() %>"/></td>
		</tr>
		<tr>
			<td>是否可以兑换吱币：</td><td><select name="canexchange"><option value="1" <%=(mi.getCanexchange()==1)?"selected":"" %>>可以</option><option value="0" <%=(mi.getCanexchange()==0)?"selected":"" %>>不可以</option></select></td>
		</tr>
		<tr>
			<td>吱币兑换比例：</td><td><input type="text" size="31" name="exchangerate" value="<%=mi.getExchangerate() %>" />倍吱币</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="提交" /> <input type="button" onclick="history.back()" value="返回"/></td>
        </tr>
    </table>
</form>
<%@ include file="../inc/footer.jsp"%>