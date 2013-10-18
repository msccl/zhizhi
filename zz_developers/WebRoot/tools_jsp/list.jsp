<%@ include file="../inc/header.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.qkzz.web.developer.bean.Tools"%>
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
List<Tools> toolsList = (List<Tools>)request.getAttribute("toolsList");
GameInfo gameInfo = (GameInfo)request.getAttribute("bean");
%>
		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="/developers/index">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="/game/list/10/1">我的游戏</a>&nbsp;&gt;&gt;&nbsp;<a href="#"><%=gameInfo.getName() %></a>&nbsp;&gt;&gt;&nbsp;游戏道具 </span>
				</div>
				
				<div class="gamenav">
					<ul class="clearfix">
						<li class="gamenav_graybt">
							<a href="/game/edit?id=<%=gameInfo.getId() %>">游戏资料</a>
						</li>
						<li class="gamenav_bluebt">
							<a href="/tools/list/<%=gameInfo.getId() %>/10/1">道具</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/store/list/<%=gameInfo.getId() %>/10/1">商店</a>
						</li>
						<!-- li class="gamenav_graybt">
							<a href="/auction/list/< %=gameInfo.getId() % >/10/1">拍卖行</a>
						</li>
						<li class="gamenav_graybt">
							<a href="#">商城</a>
						</li-->
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
											<form action="/tools/search" method="get" name="formsearch">
											<li class="left"><input type="text" value="输入道具名称" name="q"> </li>
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
											<div class="bt_l"><a class="addbg_bt" href="/tools/add?id=<%=gameInfo.getId() %>">添加道具</a></div>
											<div class="bt_r"></div>
										</div>
									</td>
								</tr>
							</tbody></table>	
						</div>
						

			<%
			if(toolsList == null || toolsList.size() == 0) {
				%><div>游戏暂无道具!</div><%
			} else {
				%>
						<div>
							<table class="list_title"><tbody>
								<tr>
									<td class="it_a"><a href="#">道具图片</a></td>
									<td class="it_b"><a href="#">道具名称</a></td>
									<td class="it_c"><a href="#">状态</a></td>
									<td class="it_d"><a href="#">编号</a></td>
									<td class="it_e"><a href="#">相关操作</a></td>
								</tr>
							</tbody></table>
				<%
				for(int i=0;i<toolsList.size();i++) {
					Tools bean = toolsList.get(i);
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
									<td class="it_a"><img src="<%=bean.getImg() %>" title="<%=bean.getName() %>"/></td>
									<td class="it_b"><%=bean.getName() %></td>
									<td class="it_c"><%=statusStr %></td>
									<td class="it_d"><%=bean.getId() %></td>
									<td class="it_e"><a href="#">商店</a> <a href="/tools/edit?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">编辑</a> <span class="del"><a href="/tools/del?gameid=<%=bean.getGameid() %>&id=<%=bean.getId()%>">删除</a></span></td>
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
							<div>>添加 / 编辑 道具</div>
						</div>
						<div class="sub_cont question">
							<div class="iteminfo">
							<table class="tab_edit" style="width:100%"><tbody>
								<tr>
									<td class="l">道具名称：</td><td class="r"><input class="textbox" value="力量之靴"/></td>
								</tr>
								<tr>
									<td class="l">道具图片：</td><td class="r"><img src="static/img/item0.png" title="力量之靴"/></td>
								</tr>
								<tr>
									<td class="l">更新图片：</td><td class="r"><input class="textbox" value="static/img/item0.png"/></td>
								</tr>
								<tr>
									<td class="l"></td>
									<td class="r">
										<div style="width:60px;" class="gbt">
											<a href="#">选择图片</a>
										</div>
									</td>
								</tr>
								<tr>
									<td class="l">道具描述：</td><td class="r"><textarea class="textbox" >使您的攻击上升5点，持续10秒。</textarea></td>
								</tr>
								<tr>
									<td class="l">允许交易：</td><td class="r"><input class="textbox" style="width:40px;" value="1"/><br/>允许为1，否则为0</td>
								</tr>
								<tr>
									<td class="l">允许拍卖：</td><td class="r"><input class="textbox" style="width:40px;" value="0"/><br/>允许为1，否则为0</td>
								</tr>
								<tr>
									<td class="l">功能键值：</td><td class="r"><textarea class="textbox" >ddd=eee</textarea></td>
								</tr>
								<tr>
									<td class="l">货币ID：</td><td class="r"><input class="textbox" value="121"/></td>
								</tr>
								<tr>
									<td class="l">价格：</td><td class="r"><input class="textbox" value="10"/></td>
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






<%@ include file="../inc/footer.jsp"%>