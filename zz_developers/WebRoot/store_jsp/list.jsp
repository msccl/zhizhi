<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.qkzz.web.developer.bean.Store"%>
<%@page import="com.qkzz.web.developer.bean.GameInfo"%>
<%@ include file="../inc/nav.jsp"%>
<%
String ERR_MSG = (String)request.getAttribute("ERR_MSG");
if(ERR_MSG != null && !ERR_MSG.equals("")) {
	if(ERR_MSG.equals("PARA_WRONG")) { 
		%><div class="dotline0">
		参数传递错误，请返回!
		</div><%
	} else if(ERR_MSG.equals("CLASSONE_NOT_EXIST")) {
		%><div class="dotline0">
		商品所属类别不存在或者已经被删除，请返回!
		</div><%
	}
	%><%@ include file="/inc/footer.jsp"%><%
	return;
}

String turnpage = (String)request.getAttribute("turnpage");
List<Store> storeList = (List<Store>)request.getAttribute("storeList");
GameInfo gameInfo = (GameInfo)request.getAttribute("bean");

%>
		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="/developers/index">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="/game/list/10/1">我的游戏</a>&nbsp;&gt;&gt;&nbsp;<a href="#"><%=gameInfo.getName() %></a>&nbsp;&gt;&gt;&nbsp;商店 </span>
				</div>
				
				<div class="gamenav">
					<ul class="clearfix">
						<li class="gamenav_graybt">
							<a href="/game/edit?id=<%=gameInfo.getId() %>">游戏资料</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/tools/list/<%=gameInfo.getId() %>/10/1">道具</a>
						</li>
						<li class="gamenav_bluebt">
							<a href="/store/list/<%=gameInfo.getId() %>/10/1">商店</a>
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
									<td>
										<ul class="clearfix">
											<form action="/stores/search" method="get" name="formsearch">
											<li class="left"><input type="text" value="输入商店名称" name="q"> </li>
											<li class="left">
												<div class="gbt">
													<a href="#" onclick="document.formsearch.submit()">搜  索</a>
												</div>
											</li>
											</form>
										</ul>
									</td>
									<td>
										<div class="bt1 right">
											<div class="bt_l"><a class="addbg_bt" href="/store/applyNew?id=<%=gameInfo.getId() %>">添加商店</a></div>
											<div class="bt_r"></div>
										</div>
									</td>
								</tr>
							</tbody></table>	
						</div>
						

			<%
			if(storeList == null || storeList.size() == 0) {
				%><div>该游戏暂无商店!</div><%
			} else {
				%>
						<div>
							<table class="list_title"><tbody>
								<tr>
									<td class="sp_a"><a href="#">商店ID</a></td>
									<td class="sp_b"><a href="#">商店名称</a></td>
									<td class="sp_c"><a href="#">状态</a></td>
									<td class="sp_d"><a href="#">回收道具</a></td>
									<td class="sp_e"><a href="#">折扣</a></td>
									<td class="sp_f"><a href="#">创建时间</a></td>
									<td class="sp_g"><a href="#">操作</a></td>
								</tr>
							</tbody></table>
				<%
				for(int i=0;i<storeList.size();i++) {
					Store bean = storeList.get(i);
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
						String canrecycle = "允许出售";
						if(bean.getCanrecycle()==0){
							canrecycle = "禁止出售";
						}
						%><table class="<%=className%>"><tbody>
								<tr>
									<td class="sp_a"><%=bean.getId() %></td>
									<td class="sp_b"><%=bean.getName() %></td>
									<td class="sp_c"><%=statusStr %></td>
									<td class="sp_d"><%=canrecycle %></td>
									<td class="sp_e"><%=bean.getRecyclerate() %></td>
									<td class="sp_f">2011-04-24 11:05:30</td>
									<td class="sp_g"><a href="#">道具</a> <a href="/store/edit?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">编辑</a> <span class="del"><a href="/store/del?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">删除</a></span></td>
									
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
				
					<!--  -->
					<div class="sub_container right">
						<div class="tit_2">
							<div>>添加 / 编辑 商店</div>
						</div>
						<div class="sub_cont question">
							<div class="iteminfo"><input type="hidden" name="id" value="${gameid}" />
							<table class="tab_edit" style="width:100%"><tbody>

								<tr>
									<td class="l">商店ID：</td><td class="r">12313</td>
								</tr>
								<tr>
									<td class="l">商店名称：</td><td class="r"><input class="textbox" value="XX店铺"/></td>
								</tr>
								<tr>
									<td class="l">状态：</td><td class="r"><img src="images/dot0.gif" title="通过"/>通过</td>
								</tr>
								<tr>
									<td class="l">回收道具：</td>
									<td class="r"><select name="canrecycle">
									<option value="1">允许</option>
									<option value="0" selected="">禁止</option>
									</select></td>
								</tr>
								<tr>
									<td class="l">折扣：</td><td class="r"><input class="textbox" style="width:40px;" value="0.2"/></td>
								</tr>
								<tr>
									<td class="l">创建时间：</td><td class="r">2011-04-24 11:05:30</td>
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
					<td align="center">< %=gameInfo.getName() % ><br/>< %=gameInfo.getGamecode() % ></td>
				</tr>
				<tr>
					<td align="center"><img src="< %=gameInfo.getLogo() % >" alt="< %=gameInfo.getName()% >"/></td>
				</tr>		
				<tr>
					<td align="center"><a href="/tools/list/< %=gameInfo.getId() % >/10/1">道具</a></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#cccccc"><b>商店</b></td>
				</tr>
				<tr>
					<td align="center"><a href="/money/list/< %=gameInfo.getId() % >/10/1">货币</a></td>
				</tr>
				<tr>
					<td align="center"><a href="/auction/list/< %=gameInfo.getId() % >/10/1">拍卖</a></td>
				</tr>
			</table>		
		</td>
		<td align="left" valign="top">< %
			% ><table width="100%">
				<tr>
					<td><form action="/tools/search" method="get"><input type="text" name="q"><input type="radio" name="type" value="0">商店名<input type="radio" name="type" value="1">道具名<input type="submit" name="submit" value="搜索"></form></td>
					<td><a href="/store/applyNew?id=< %=gameInfo.getId() %>">添加</a></td>
				</tr>
			</table>< %
			
			if(storeList == null || storeList.size() == 0) {
				% ><div>游戏暂无商店!</div>< %
			} else {
				% ><table align="center" width="100%" border="1">
					<tr align="center">
						<td>商店ID</td>
						<td>所属游戏ID</td>
						<td>商店名称</td>
						<td>状态</td>
						<td>商店swf</td>
						<td>可否售卖道具</td>
						<td>折扣</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
				< %
				for(int i=0;i<storeList.size();i++) {
					Store bean = storeList.get(i);
					if(bean != null) {
						int status = bean.getStatus();
						String statusStr ="等待审核";
						if(status == 1) {
							statusStr = "审核通过";
						} else if(status == -1) {
							statusStr = "审核未通过";
						}
						String canrecycle = "允许出售";
						if(bean.getCanrecycle()==0){
							canrecycle = "禁止出售";
						}
						% ><tr align="center">
							<td>< %=bean.getId() %></td>
							<td>< %=bean.getGameid() % ></td>
							<td>< %=bean.getName() %></td>
							<td>< %=statusStr %></td>
							< !--td><img src="< %=bean.getSwfurl() %>" alt="" /></td- ->
							<td>< %=canrecycle %></td>
							<td>< %=bean.getRecyclerate() %></td>
							<td>< %=bean.getCreatetime() %></td>
							<td><a href="/store/edit?gameid=< %=bean.getGameid() %>&id=< %=bean.getId()%>">编辑</a> <a href="/store/del?gameid=< %=bean.getGameid() %>&id=< %=bean.getId()%>">删除</a></td>
						</tr>< %
					}
				}	
				% >
					<tr>
						<td colspan="7" align="center">< %=turnpage %></td>
					</tr>		
				</table>< %
			}

		% ></td>	
	</tr>
</table>-->

<%

%><%@ include file="../inc/footer.jsp"%>