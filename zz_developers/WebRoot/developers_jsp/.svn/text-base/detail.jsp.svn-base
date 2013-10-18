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
	%>
		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="#">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="#">帐户信息</a></span>
				</div>

				<!--  -->
				<div class="clearfix">
					<div class="game_container left">
						
						<div class="gameinfo">
							<form action="/developers/edit" method="post" enctype ="multipart/form-data" name="editDevelopers">
							<input type="hidden" name="id" value="<%=ub.getId()%>" />
							<table class="tab_edit" style="width:100%"><tbody>
								<tr>
									<td class="l">开发商名称：</td><td class="r"><input class="textbox" name="name" value="<%=ub.getName() %>"/></td>
								</tr>
								
								<tr>
									<td class="l">简介：</td><td class="r"><textarea class="textbox" name="intro" ><%=ub.getIntro() %></textarea></td>
								</tr>
								<tr>
									<td class="l">开发商成员简介：</td><td class="r"><textarea class="textbox" name="members" ><%=ub.getMembers() %></textarea></td>
								</tr>
								<tr>
									<td class="l">所在省：</td><td class="r"><input style="width:180px;" class="textbox" name="provinceid" value="<%=ub.getProvinceid() %>"/></td>
								</tr>
								<tr>
									<td class="l">所在市：</td><td class="r"><input style="width:180px;" class="textbox" name="cityid" value="<%=ub.getCityid() %>"/></td>
								</tr>
								<tr>
									<td class="l">地址：</td><td class="r"><input class="textbox" name="address" value="<%=ub.getAddress() %>"/></td>
								</tr>
								<tr>
									<td class="l">电话：</td><td class="r"><input style="width:180px;" class="textbox" name="phone" value="<%=ub.getPhone() %>"/></td>
								</tr>
								<tr>
									<td class="l">Email：</td><td class="r"><%=ub.getEmail() %></td>
								</tr>
								<tr>
									<td class="l">开发商标签：</td><td class="r"><input class="textbox" name="tags" value="<%=ub.getTags() %>"/></td>
								</tr>
								<tr>
									<td class="l">注册时间：</td><td class="r"><%=ub.getCreatetime() %></td>
								</tr>

								<tr>
									<td class="l">开发商logo：</td><td class="r"><input type="file" name="logo" /><img src="<%=ub.getLogo() %>" border="1"/></td>
								</tr>
								<tr>
									<td class="l">更新logo：</td><td class="r"><input class="textbox" value="<%=ub.getLogo() %>" readonly/></td>
								</tr>
								
								
							</tbody></table>
							
							<div class="t_center"><div class="bbt" style="width:80px;margin: 0 auto;">
							<a href="#" onclick="document.editDevelopers.submit()">保存</a>
							</div></div>
							</form>
						</div>
						
						<div>
							
						</div>

						
					</div>
				
					<!--  -->
					<div class="sub_container2 right">
						<div class="tit_2">
							>开发商等级：
						</div>
						<div class="question">
							<span class="blue1"><b><%=ub.getGrade() %>二级开发商(3000分) <span class="orange">★★★★★</span></b></span><br/><br/>
							如下指数影响开发商等级：<br/>
							<span class="gray12">--开发商的游戏数量</span><br/>
							<span class="gray12">--游戏访问量</span><br/>
							<span class="gray12">--虚拟物品消费数</span>
						</div>
						<div class="tit_2">
							>吱吱游戏联盟：
						</div>
						<div class="question">
							开发商等级达到四级，可以申请成为吱吱游戏联盟成员<br/><br/>
							<p>
							联盟成员权益：<br/>
							<span class="red1">--更高的收益分成比例</span><br/>
							<span class="red1">--游戏运营推广服务</span><br/>
							<span class="red1">--更高渠道流量保证</span><br/>
							<span class="red1">--收益风险补偿</span><br/><br/>

							</p>
						</div>

						
					</div>

				</div>

			</div>
			<!--  -->


		</div>
	
	<%
}
%>
		

<%@ include file="../inc/footer.jsp" %>