<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@page import="com.qkzz.web.developer.bean.Developers"%>
<%@ include file="../inc/header.jsp"%><%
Developers info = (Developers)request.getAttribute("info");
String passwordc = (String)request.getAttribute("passwordc");
%>
		
		<div class="header">
			<div class="top">
				
			</div>

			<div class="navline"></div>
			<div class="search">
			</div>
		</div>
		<div class="main">
			
			<!--  -->
			<div class="container cloud" style="margin:0 0 0 45px;width:955px;">
				
				<!--  -->	
				<div class="middle" style="width:402px;">
					<div class="regmod">

							<div class="logtop"></div>

							<form action="/developers/register" method="post">
							<div class="regmiddle">
								请确认信息
								<table class="tab_reg" style="width:100%"><tbody>
									<tr>
										<td class="l">邮箱(*)：</td><td class="r"><input style="width:180px;" type="text" name="email" value="<%=info.getEmail()%>"/></td>
									</tr>
									<tr>
										<td class="l" style="padding-bottom:0;">登录密码(*)：</td><td class="r" style="padding-bottom:0;"><input style="width:180px;" type="password" name="password" value="<%=info.getPassword()%>"/><br/>区分大小写,注意空格与特殊字符)</td>
									</tr>
									<tr>
										<td class="l">确认密码(*)：</td><td class="r"><input style="width:180px;" type="password" name="passwordc" value="<%=passwordc%>"/></td>
									</tr>
									<tr>
										<td class="l">验证码：</td><td class="r"><input style="margin-bottom:4px;" type="text" name="valicode" value="" maxlength="4"/><br/><img src="/validate" alt="验证码"/><a href="/">再换一张</a></td>
									</tr>
									
								</tbody></table>
							</div>
							<div class="regbottom">
								<div class="regtip">
									<span></span><br/><br/>
									<input type="image" class="regbt" src="/images/regbt1.gif" style="border-width:0px;" name="submit">
								</div>
							</div>
							</form>

					</div>
					
				</div>

				

			</div>
			<!--  -->

		</div>

<%@ include file="../inc/footer.jsp"%>