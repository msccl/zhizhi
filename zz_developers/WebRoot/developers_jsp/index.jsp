<%@ include file="../inc/header.jsp" %><%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.qkzz.web.developer.bean.Developers"%>
<%@ include file="../inc/nav.jsp" %><%
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
				
				<!--  -->
				<div class="clearfix">
					<div class="game_container left" style="width:540px;">

						<div class="tit_1">游戏信息</div>
						<div class="game_info">
							您当前已经有 0 款游戏  <span class="newgame"><a href="/game/add"><img src="/images/newgame.gif"/></a></span><br/>
							开发中(个)：0|已发布(个)：0|更新中(个)：0<br/>
							审核中(个)：0|已通过(个)：0|未通过(个)：0<br/>
							
						</div>


						<div class="tit_1" style="margin-top:20px;">开发支持</div>
						<div class="ul_zxLst1">
							<ul class="clearfix">
								<li>
									<div class="img_zxLst"> <img src="/images/icon_API_64x64.png " alt=""> </div>
									<div class="ifo_zxlst">
										<h5><a href="/wiki/index.php/API文档">API文档</a></h5>
										<div class="case_zx">API接口描述及说明文档，包括基础数据API、搜索API和位置信息API</div>
									</div>
								</li>
								<li class="lisep_rt">
									<div class="img_zxLst"> <img src="/images/icon_SDK_64x64.png " alt=""> </div>
									<div class="ifo_zxlst">
										<h5><a href="/wiki/index.php/SDK">SDK下载</a></h5>
										<div class="case_zx">开发工具包，包括Adobe Air、PHP、Python、Java等流行语言。</div>
									</div>
								</li>
								<li>
									<div class="img_zxLst"> <img src="/images/icon_cjwt_64x64.png " alt=""> </div>
									<div class="ifo_zxlst">
										<h5><a href="/wiki/index.php/FAQ">常见问题</a></h5>
										<div class="case_zx">开发，审核，接口权限、应用调优等常见问题</div>
									</div>
								</li>
								<li class="lisep_rt">
									<div class="img_zxLst"> <img src="/images/icon_forum_64x64.png " alt=""> </div>
									<div class="ifo_zxlst">
										<h5><a href="#">交流论坛</a></h5>
										<div class="case_zx">开发者技术交流园地，提供运营商务支持。</div>
									</div>
								</li>
							</ul>
						</div>
						
						<div>
							<div class="tit_1">吱吱入门</div>
							
							<p><b><span class="gray12">初始化吱吱</span></b><br/>
							<span class="red1">initSys( zzContainer, "1C7473B9011CD08324CE0A23DC7D86DD", 700, 530,...</span><br/>
							上面参数依次是吱吱容器id，游戏的id号，游戏高度，游戏宽度</p>
							 
							<p style="padding:10px 0;"><b><span class="gray12">自定义吱吱界面坐标：</span></b></p>

							<p style="padding-left:10px;"><b><span class="gray12">-- 菜单模块</span></b>(包括大厅，商店，背包，好友面板的图标)<br/>
							<span class="red1">"MenuPanel" : [MenuPanel,530,498,295,500]</span><br/>
							上面四个数字，分别是菜单x坐标，y坐标，退出按钮的x坐标，退出按钮的y坐标</p>

							<p style="padding-left:10px;"><b><span class="gray12">-- 聊天模块</span></b><br/>
							<span class="red1">"ChatPanel"  : [ChatPanel,0,345]</span><br/>
							上面两个数字，分别是聊天界面的x坐标，y坐标</p>

							<div class="tit_1" style="margin-top:20px;">吱吱相关</div>
							 
							<p >可以修改吱吱的界面皮肤：下载 <a href="http://www.zizi.im/zizi/down/ziziUI.fla" target="_blank">ziziUI.fla</a> ，修改后输出 UI.swc文件。<br/>
							可以参考的吱吱界面布局：<a href="http://www.zizi.im" target="_blank">吱吱游戏</a><br/>
							下载吱吱示例： <a href="http://www.zizi.im/zizi/down/demo_1.20.zip" target="_blank">demo</a></p>
							
						</div>

					</div>
				
					<!--  -->
					<div class="sub_container2 right">
						<div class="tit_2">
							>商店道具接口
						</div>

						<div class="tit_2">
							>支付接口
						</div>

						<div class="tit_2">
							>聊天接口
						</div>

						<div class="tit_2">
							>好友接口
						</div>

						<div class="tit_2">
							>信件接口
						</div>

						<div class="question">
							<b>典型问题：</b><br/>
							什么是吱吱互动游戏引擎？<br/>
							吱吱引擎有哪些API?<br/>
							如何申请成为吱吱的有效开发商用户？<br/>
							吱吱有没有广告接口？<br/>
							开发商可以在游戏中加自己的广告吗？<br/>
							什么时候可以申请游戏收益付款？<br/>
							游戏收益如何分成？<br/>
							如何去掉图标菜单面板？<br/>
							如何自定义退出按钮的位置？<br/>
						</div>

					</div>

				</div>

			</div>
			<!--  -->


		</div>

	
	<%
}
%><%@ include file="../inc/footer.jsp" %>