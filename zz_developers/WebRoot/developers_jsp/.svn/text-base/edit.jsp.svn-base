<%@ include file="../inc/header.jsp" %><%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.qkzz.web.developer.bean.Developers"%>
<%@ include file="../inc/nav.jsp" %>
<%
Developers ub = (Developers)request.getAttribute("user");

if(ub == null) {
	%><div class="dotline0">
	参数传递错误，请返回!
	</div><%
} else {
	%><form method="post" action="/user/edit"><table>
		<tr>
			<td colspan="2">用户信息</td>
		</tr>
		<tr>
			<td>开发商名称:</td><td><input type="text" value="<%=ub.getName() %>" name="name" /></td>
		</tr>
		<tr>
			<td>简介:</td><td><textarea rows="5" cols="10" name="intro"><%=ub.getIntro() %></textarea></td>
		</tr>
		<tr>
			<td>所在省ID:</td><td><%=ub.getProvinceid() %><select name="provinceid"><option value=""></option></select></td>
		</tr>
		<tr>
			<td>所在市ID:</td><td><%=ub.getCityid() %><select name="cityid"><option value=""></option></select></td>
		</tr>
		<tr>
			<td>地址:</td><td><input type="text" value="<%=ub.getAddress() %>" name="address"/></td>
		</tr>
		<tr>
			<td>开发商成员简介:</td><td><textarea rows="5" cols="10" name="members"><%=ub.getMembers() %></textarea></td>
		</tr>
		<tr>
			<td>电话:</td><td><input type="text" value="<%=ub.getPhone() %>" name="phone"></td>
		</tr>
		<tr>
			<td>Email:</td><td><input type="text" value="<%=ub.getEmail() %>" name="email"></td>
		</tr>
		<tr>
			<td>开发商标签:</td><td><input type="text" value="<%=ub.getTags() %>" name="tags"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="提交"></td>
		</tr>
	</table></form><%
}
%><%@ include file="../inc/footer.jsp" %>