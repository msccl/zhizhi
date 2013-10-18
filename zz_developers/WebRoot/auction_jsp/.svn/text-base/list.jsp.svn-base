<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.qkzz.web.developer.bean.Auction"%>
<%@page import="com.qkzz.web.developer.bean.GameInfo"%>
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

String turnpage = (String)request.getAttribute("turnpage");
List<Auction> auctionList = (List<Auction>)request.getAttribute("auctionList");
GameInfo gameInfo = (GameInfo)request.getAttribute("bean");

%>
		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="/developers/index">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="/game/list/10/1">我的游戏</a>&nbsp;&gt;&gt;&nbsp;<a href="#"><%=gameInfo.getName() %></a>&nbsp;&gt;&gt;&nbsp;拍卖行 </span>
				</div>
				
				<div class="gamenav">
					<ul class="clearfix">
						<li class="gamenav_graybt">
							<a href="/game/edit?id=<%=gameInfo.getId() %>">游戏资料</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/tools/list/<%=gameInfo.getId() %>/10/1">道具</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/store/list/<%=gameInfo.getId() %>/10/1">商店</a>
						</li>
						<li class="gamenav_bluebt">
							<a href="/auction/list/<%=gameInfo.getId() %>/10/1">拍卖行</a>
						</li>
						<li class="gamenav_graybt">
							<a href="#">商城</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/money/list/<%=gameInfo.getId() %>/10/1">货币</a>
						</li>
					</ul>
				</div>
				<div class="gamenav_line"></div>

				<!--  -->
				<div class="clearfix">
					<div class="game_container left">
						
						<div class="it_action">
							<table><tbody>
								<tr>
									<td><!--
										<ul class="clearfix">
											<form action="/tools/search" method="get" name="formsearch">
											<li class="left"><input type="text" value="输入拍卖行名称" name="q"> </li>
											<li class="left">
												<div class="gbt">
													<a href="#" onclick="document.formsearch.submit()">搜  索</a>
												</div>
											</li>
											</form>
										</ul>-->
									</td>
									<td>
										<div class="bt1 right">
											<div class="bt_l"><a class="addbg_bt" href="/auction/applyNew?id=<%=gameInfo.getId() %>">添加拍卖行</a></div>
											<div class="bt_r"></div>
										</div>
									</td>
								</tr>
							</tbody></table>	
						</div>
						

			<%
			if(auctionList == null || auctionList.size() == 0) {
				%><div>该游戏暂无拍卖行!</div><%
			} else {
				%>
						<div>
							<table class="list_title"><tbody>
								<tr>
									<td class="it_b"><a href="#">道具名称</a></td>
									<td class="it_c"><a href="#">状态</a></td>
									<td class="it_d"><a href="#">编号</a></td>
									<td class="it_e"><a href="#">相关操作</a></td>
								</tr>
							</tbody></table>
				<%
				for(int i=0;i<auctionList.size();i++) {
					Auction bean = auctionList.get(i);
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
						%><table class="<%=className%>"><tbody>
								<tr>
									<td class="it_b"><%=bean.getName() %></td>
									<td class="it_c"><%=statusStr %></td>
									<td class="it_d"><%=bean.getId() %></td>
									<td class="it_e"><a href="#">道具</a> <a href="/auction/edit?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">编辑</a> <span class="del"><a href="/auction/del?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">删除</a></span></td>
								</tr>
							</tbody></table>
						<%
					}
				}	
				%>
						</div>
						<div class="pagenav">
						<%=turnpage %>
							<span class="pre bbt"><a href="#"><<</a></span>
								<a href="#">1</a> <a href="#">2</a> <a class="pagenow" href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
							<span class="next bbt"><a href="#">>></a></span>
						</div>
				<%
			}
			%>



						
						
						
						
						
					</div>
				
					<!--  -->
					<div class="sub_container right">
						<div class="tit_2">
							<div>>添加 / 编辑 拍卖行</div>
						</div>
						<div class="sub_cont question">
							<div class="iteminfo"><input type="hidden" name="id" value="${gameid}" />
							<table class="tab_edit" style="width:100%"><tbody>
        <tr>
            <td class="l">名称：</td><td class="r"><input type="text" size="31" name="name"/></td>
      	</tr>
							</tbody></table>
							</div>		
						</div>

						<div class="save bbt"><a href="#">保存</a></div>
					</div>

				</div>

			</div>
			<!--  -->


		</div>




<!--
<table width="100%">
	<tr>
		<td valign="top" width="15%">
			<table width="100%" border="1">
				<tr>
					<td align="center"><%=gameInfo.getName() %><br/><%=gameInfo.getGamecode() %></td>
				</tr>
				<tr>
					<td align="center"><img src="<%=gameInfo.getLogo() %>" alt="<%=gameInfo.getName()%>"/></td>
				</tr>		
				<tr>
					<td align="center"><a href="/tools/list/<%=gameInfo.getId() %>/10/1">道具</a></td>
				</tr>
				<tr>
					<td align="center"><a href="/store/list/<%=gameInfo.getId() %>/10/1">商店</a></td>
				</tr>
				<tr>
					<td align="center"><a href="/money/list/<%=gameInfo.getId() %>/10/1">货币</a></td>
				</tr>
				<tr>
					<td align="center"><b>拍卖</b></td>
				</tr>
			</table>		
		</td>
		<td align="left" valign="top"><%
			%><table width="100%">
				<tr>
					<td><form action="/tools/search" method="get"><input type="text" name="q"><input type="submit" name="submit" value="搜索"></form></td>
					<td><a href="/auction/applyNew?id=<%=gameInfo.getId() %>">添加</a></td>
				</tr>
			</table><%
			
			if(auctionList == null || auctionList.size() == 0) {
				%><div>游戏暂无拍卖行!</div><%
			} else {
				%><table align="center" width="100%" border="1">
					<tr align="center">
						<td>拍卖行ID</td>
						<td>拍卖行名称</td>
						<td>状态</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
				<%
				for(int i=0;i<auctionList.size();i++) {
					Auction bean = auctionList.get(i);
					if(bean != null) {
						int status = bean.getStatus();
						String statusStr ="等待审核";
						if(status == 1) {
							statusStr = "审核通过";
						} else if(status == -1) {
							statusStr = "审核未通过";
						}
						%><tr align="center">
							<td><%=bean.getId() %></td>
							<td><%=bean.getName() %></td>
							<td><%=statusStr %></td>
							<td><%=bean.getCreatetime() %></td>
							<td><a href="/auction/edit?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">编辑</a> <a href="/auction/del?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">删除</a></td>
						</tr><%
					}
				}	
				%>
					<tr>
						<td colspan="5" align="center"><%=turnpage %></td>
					</tr>		
				</table><%
			}


		%></td>	
	</tr>
</table>
-->

<%

%><%@ include file="../inc/footer.jsp"%>