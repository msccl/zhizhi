<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ include file="../inc/header.jsp"%>
		
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

							<form action="/developers/login" method="post">
							<input style="display:none;" type="hidden" name="url" value="${url}"/>
							<div class="regmiddle">

								<table class="tab_reg" style="width:100%"><tbody>
									
									<tr>
										<td class="l">Email(*)：</td><td class="r"><input style="width:180px;" type="text" name="email" value=""/></td>
									</tr>
									<tr>
										<td class="l" style="padding-bottom:0;">登录密码(*)：</td><td class="r" style="padding-bottom:0;"><input style="width:180px;" type="password" name="password" value=""/><br/>区分大小写,注意空格与特殊字符)</td>
									</tr><!--
									<tr>
										<td class="l">验证码：</td><td class="r"><input type="text" name="valicode" value="" maxlength="4"/><br/><img src="/validate" alt="验证码"/><a href="/developers/login${en_url }">再换一张</a></td>
									</tr>-->
									<tr>
										<td class="l"></td><td class="r">还没有dev.zizi.im账号？ <a href="/developers/register">注册?</a></td>
									</tr>
									
								</tbody></table>
								
							</div>
							<div class="regbottom">
								<div class="regtip">
									<span><%if(request.getAttribute("ERR_MSG")!=null){ %>${ERR_MSG}<br/><%} %></span><br/><br/>
									<input name="submit" type="image" class="regbt" src="/images/loginbt2.gif" style="border-width:0px;">
								</div>
							</div>
							</form>

					</div>
					
				</div>

				

			</div>
			<!--  -->

		</div>

<%@ include file="../inc/footer.jsp"%>