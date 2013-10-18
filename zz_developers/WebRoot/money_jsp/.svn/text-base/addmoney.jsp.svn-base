<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../inc/nav.jsp"%>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="/money/add" method="post">
    <input type="hidden" name="id" value="${gameid}" />
    <table cellpadding=0 cellspacing=0>
        <tr>
            <td style="height:30px;">货币名称：</td><td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>是否可以兑换吱币：</td><td><select name="canexchange"><option value="1">可以</option><option value="0">不可以</option></select></td>
		</tr>
		<tr>
			<td>吱币兑换比例：</td><td><input type="text" name="exchangerate" value="1" />倍吱币</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="添加" /> <input type="button" onclick="history.back()" value="返回"/></td>
        </tr>
    </table>
</form>
<%@ include file="../inc/footer.jsp"%>