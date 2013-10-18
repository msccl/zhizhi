<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/header.jsp"%>

		<div class="header">
			<div class="top">
				<div id="login">
					<ul class="clearfix">
				<form action="/developers/login" method="post">
						<li style="padding:3px 0 0 0px;"><input class="logininput" type="text" name="email" placeholder="邮箱"/></li>
						<li style="padding:3px 0 0 11px;"><input class="logininput" type="password" name="password" placeholder="密码"/></li>
						<li style="padding:3px 0 0 6px;"><input type="image" class="loginbt" src="/images/loginbt1.gif" style="border-width:0px;" name="submit"></li>
				</form>
					</ul>
				</div>
			</div>

			<div class="navline"></div>
			<div class="search">
			</div>
		</div>
		<div class="main">
			
			<!--  -->
			<div class="container cloud" style="margin:0 0 0 45px;width:955px;">
				
				<!--  -->
				<div class="clearfix">
					<div class="left" style="width:548px;">
						<div class="zilist">
							<ul  class="clearfix">
								<li class="zi_l">
									<div class="zi01"><div class="zititle">跨平台，跨游戏</div>跨发布商平台，跨浏览器，跨客户端程序的，同一台机器上实现多点登录。</div>
									<div class="zi04"><div class="zititle">互动聊天</div>玩家感觉不到不同技术环境的平滑过渡，可以自由，流畅的互动交流，互助协作。</div>
									<div class="zi05"><div class="zititle">社交好友</div>好友模块包括完备的社交功能：查找玩家，导入社交网络玩家，邀请同学、朋友。</div>		
									<div class="zi07"><div class="zititle">荣誉成就</div>游戏成就由开发商个性化打造，让玩家可以充分体验游戏的内容特色与趣味性。</div>
								</li>
								<li class="zi_r">
									<div class="zi02"><div class="zititle">微网络游戏</div>微支付游戏，轻网游，接口简单易用，自由打造自我风格的吱吱应用界面。</div><div class="zi03"><div class="zititle">微社交游戏</div>趣味十足的微社交游戏，完全突破了传统的社交游戏，玩家之间更多的沟通，协作，分享。</div>
									<div class="zi06"><div class="zititle">道具商店，拍卖行</div>以跨越、兼容的模式帮助开发者开发出多样化的道具收费模式。</div>
									<div class="zi08"><div class="zititle">积分排行</div>吱吱为每一个游戏，每一位玩家保存游戏积分。让游戏充满乐趣和挑战的是多人联网游戏！</div>
								</li>
							</ul>
						</div>
					</div>
				
					<!--  -->
					<div class="right" style="width:402px;">
						<div class="regmod">
							<div class="regtop">
										
							</div>
							<form action="/developers/registerConfirm" method="post">
							<div class="regmiddle">
							<%if(request.getAttribute("ERR_MSG")!=null){ %><strong>${ERR_MSG}</strong><br/><%} %>
								<table class="tab_reg" style="width:100%"><tbody>
									<tr>
										<td class="l">邮箱(*)：</td><td class="r"><input style="width:180px;" type="text" name="email"/></td>
									</tr>
									<tr>
										<td class="l" style="padding-bottom:0;">登录密码(*)：</td><td class="r" style="padding-bottom:0;"><input style="width:180px;" type="password" name="password"/><br/>区分大小写,注意空格与特殊字符)</td>
									</tr>
									<tr>
										<td class="l">确认密码(*)：</td><td class="r"><input style="width:180px;" type="password" name="passwordc"/></td>
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

			</div>
			<!--  -->


		</div>

<%@ include file="../inc/footer.jsp"%>