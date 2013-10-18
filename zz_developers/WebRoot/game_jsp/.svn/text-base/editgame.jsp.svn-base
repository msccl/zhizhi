<%@ include file="../inc/header.jsp" %><%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/nav.jsp" %>

		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="/developers/index">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="/game/list/10/1">我的游戏</a>&nbsp;&gt;&gt;&nbsp;${bean.name} </span>
				</div>

				<div class="gamenav">
					<ul class="clearfix">
						<li class="gamenav_bluebt">
							<a href="/game/edit?id=${bean.id}">游戏资料</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/tools/list/${bean.id}/10/1">道具</a>
						</li>
						<li class="gamenav_graybt">
							<a href="/store/list/${bean.id}/10/1">商店</a>
						</li>
						<!-- li class="gamenav_graybt">
							<a href="/auction/list/${bean.id}/10/1">拍卖行</a>
						</li>
						<li class="gamenav_graybt">
							<a href="#">商城</a>
						</li-->
						<li class="gamenav_graybt">
							<a href="/money/list/${bean.id}/10/1">货币</a>
						</li>
					</ul>
				</div>
				<div class="gamenav_line"></div>

				<!--  -->
				<div class="clearfix">
					<div class="game_container left">
						<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
						<form action="/game/edit" id="gameEditForm" method="post" enctype ="multipart/form-data" name="formedit">
						<input type="hidden" name="id" value="${bean.id}" />
						
						<div class="gameinfo">
							
							<table class="tab_edit" style="width:100%"><tbody>
								<tr>
									<td class="l">游戏名称：</td><td class="r"><input class="textbox" value="${bean.name}" type="text" name="name" id="gName0"/></td>
								</tr>
								<tr>
									<td class="l">游戏图片：</td><td class="r ginfo_img"><img src="${bean.logo}"/></td>
								</tr>
								<tr>
									<td class="l">更新图片：</td><td class="r"><input class="textbox" value="${bean.logo}" readonly/></td>
								</tr>
								<tr>
									<td class="l"></td><td class="r"><input type="file" size="31" name="image"/></td>
								</tr>
								<tr>
									<td class="l">游戏描述：</td><td class="r"><textarea class="textbox" name="intro">${bean.intro}</textarea></td>
								</tr>
								<tr>
									<td class="l">游戏玩法：</td><td class="r"><textarea class="textbox" name="strategy">${bean.strategy}</textarea></td>
								</tr>
								<tr>
									<td class="l">游戏UI地址：</td><td class="r"><input class="textbox" value="${bean.uiurl}" type="text" name="uiurl" id="gName0"/></td>
								</tr>
								<tr>
									<td class="l">游戏地址：</td><td class="r"><input class="textbox" type="text" name="url" value="${bean.url}"/></td>
								</tr>
								<tr>
									<td class="l">游戏状态：</td><td class="r"><span class="green1">审核通过</span> <span class="red1">审核未通过</span></td>
								</tr>
								<tr>
									<td class="l">添加时间：</td><td class="r">${bean.createtime}</td>
								</tr>
								
								
							</tbody></table>

							<div style="margin-left:260px;"><input type="submit" value="保存" id="geSubmit"><span id="gameEditMsg" class="msgBox2"></span></div>
						</div>
						</form>
						<div>
							
						</div>

						
					</div>
				
					<!--  -->
					<div class="sub_container2 right">
						<div class="tit_2" id="gName1">
							>${bean.name} 游戏ID：
						</div>
						<div class="question">
							<span class="red1"><b>${bean.gamecode}</b></span>
						</div>
						<div class="tit_2">
							>游戏ID在哪使用：
						</div>
						<div class="question">
							<p><span class="blue1"><b>初始化吱吱==></b></span><br/>
							initSys( zzContainer, <span class="red1"><b>'${bean.gamecode}'</b></span>, 700, 530,...<br/>
							上面参数依次是吱吱容器id，游戏的id号，游戏高度，游戏宽度</p>
						</div>

						<div class="question">
							<b>注意：</b>
							<p>请妥善保管好你的游戏ID，不要将你的游戏及与其对应的游戏ID泄漏出去，以免给你带来不必要的损失。</p>
						</div>
					</div>

				</div>

			</div>
			<!--  -->


		</div>


<%@ include file="../inc/footer.jsp" %>