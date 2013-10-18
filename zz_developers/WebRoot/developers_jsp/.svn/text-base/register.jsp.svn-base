<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ include file="../inc/header.jsp"%>

		<div class="header">
			<div class="top"></div>

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
						<div class="regtop">
									
						</div>
						<form action="/developers/registerConfirm" method="post">
						<div class="regmiddle">
						<%if(request.getAttribute("ERR_MSG")!=null){ %><strong>${ERR_MSG}</strong><br/><%} %>
							<table class="tab_reg" style="width:100%"><tbody>
								<tr>
									<td class="l">Email(*)：</td><td class="r"><input style="width:180px;" type="text" name="email"/></td>
								</tr>
								<tr>
									<td class="l" style="padding-bottom:0;">登录密码(*)：</td><td class="r" style="padding-bottom:0;"><input style="width:180px;" type="password" name="password"/><br/>区分大小写,注意空格与特殊字符)</td>
								</tr>
								<tr>
									<td class="l">确认密码(*)：</td><td class="r"><input style="width:180px;" type="password" name="passwordc"/></td>
								</tr>
								<tr>
									<td class="l"></td><td class="r"><a href="/developers/login">已注册?登录</a></td>
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