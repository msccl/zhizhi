<%@ include file="../inc/header.jsp" %><%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/nav.jsp" %>

		<div class="main">
			
			<!--  -->
			<div class="container">
				<div class="level">			
					<span class="gray12"><a href="#">我的首页</a>&nbsp;&gt;&gt;&nbsp;<a href="#">我的游戏</a>&nbsp;&gt;&gt;&nbsp;新建游戏 </span>
				</div>


				<!--  -->
				<div class="clearfix">
					<div class="game_container left">
						<div class="tit_1">+ 新建游戏</div>
								
						<div class="flow"><span id="add_flow1" class="red1">建立游戏</span> <img src="/images/i_smallarrow.gif"/> <span id="add_flow2" class="gray12">嵌入吱吱代码</span> <img src="/images/i_smallarrow.gif"/> <span id="add_flow3" class="gray12">提交游戏文件</span></div>
						
						<div id="step1">
							<div class="tit_1">> 输入游戏资料</div>

							<div class="gameinfo">
								<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
								<form action="/game/add" id="editGameForm" method="post" enctype ="multipart/form-data">
								
								<table class="tab_edit" style="width:100%"><tbody>
									<tr>
										<td class="l">游戏名称：<span class="red1">*</span></td><td class="r"><input class="textbox" type="text" name="name"/><br/>
										建议不超过8个汉字
										</td>
									</tr>
									<tr>
										<td class="l">游戏宽度(px)：<span class="red1">*</span></td><td class="r"><input class="textbox" name="width"/><br/>
										550px ≤ 游戏宽度 ≤ 1000px
										</td>
									</tr>
									<tr>
										<td class="l">游戏高度(px)：<span class="red1">*</span></td><td class="r"><input class="textbox" name="height"/><br/>
										400px ≤ 游戏高度 ≤ 800px
										</td>
									</tr>
									
									<tr>
										<td class="l">游戏描述：</td><td class="r"><textarea class="textbox" name="intro"></textarea></td>
									</tr>
									<tr>
										<td class="l">游戏玩法：</td><td class="r"><textarea class="textbox" name="strategy"></textarea></td>
									</tr>
									<tr>
										<td class="l">游戏UI地址：<span class="red1">*</span></td><td class="r"><input class="textbox" type="text" name="uiurl"/></td>
									</tr>
									<tr>
										<td class="l">游戏图片：<span class="red1">*</span></td><td class="r"><input class="textbox" type="file" name="image" /></td>
									</tr>
									
									<tr>
										<td  class="l"></td><td class="r"><input name="ruleInput" id="ruleInputId" type="checkbox" class="check" value="" checked="checked"><label>我已经阅读并接受<a href="#" target="_blank" >《吱吱开发商用户协议》</a></label></td>
									</tr>
									<tr>
										<td>
										<input type="hidden" name="url" value="" />
										<input type="hidden" name="host" value="" />
										</td>
									</tr>
									
								</tbody></table>

								<div class="t_center">
									<div class="subtip">
										<input type="image" id="addGameSubmit" class="regbt" src="/images/subbt1.gif" style="border-width:0px;"><span id="gameAddMsg" style="display:none;"></span>
									</div>
								</div>
								</form>
							</div>
						</div>
						
						<div id="step2" style="display:none;">
							<div class="tit_1">> 嵌入吱吱代码</div>

							<div class="msgBox"><span class="gray12">游戏已经建立，你的游戏id是：</span><br/>
								<span class="red2" id="backGameid1">1C7473B9011CD08324CE0A23DC7D86DD</span><br/>

								<span class="gray12">在游戏中嵌入吱吱，要求确保吱吱处于显示层级的最上层。</span>
							</div>
								
							<div>
								<span class="gray12"><b>导入吱吱sdk：</b></span><br/>
								请参考示例，将ruiji_zhizhi.swc导入游戏项目<br/><br/>

								<span class="gray12"><b>导入吱吱UI：</b></span><br/>
								请参考示例，将UI.swc导入游戏项目<br/><br/>

								<span class="gray12"><b>import吱吱的类文件：</b></span><br/>
								import ruiji.zhizhi.as3.IMod;<br/>
								import ruiji.zhizhi.as3.ZZ;<br/><br/>
							</div>
		
							<p><b><span class="gray12">初始化吱吱</span></b><br/>
							<span class="red1">initSys( zzContainer, "<span id="backGameid2">1C7473B9011CD08324CE0A23DC7D86DD</span>", 700, 530,...</span><br/>
							上面参数依次是吱吱容器id，游戏的id号，游戏高度，游戏宽度</p>
							 
							<p style="padding:10px 0;"><b><span class="gray12">自定义吱吱界面坐标：</span></b></p>

							<p style="padding-left:10px;"><b><span class="gray12">-- 菜单模块</span></b>(包括大厅，商店，背包，好友面板的图标)<br/>
							<span class="red1">"MenuPanel" : [MenuPanel,530,498,295,500]</span><br/>
							上面四个数字，分别是菜单x坐标，y坐标，退出按钮的x坐标，退出按钮的y坐标</p>

							<p style="padding-left:10px;"><b><span class="gray12">-- 聊天模块</span></b><br/>
							<span class="red1">"ChatPanel"  : [ChatPanel,0,345]</span><br/>
							上面两个数字，分别是聊天界面的x坐标，y坐标</p>

							<div class="tit_1" style="margin-top:20px;">+ 吱吱相关</div>
							 
							<p >可以修改吱吱的界面皮肤：下载 <a href="http://www.zizi.im/zizi/down/ziziUI.fla" target="_blank">ziziUI.fla</a> ，修改后输出 UI.swc文件。<br/>
							可以参考的吱吱界面布局：<a href="http://www.zizi.im" target="_blank">吱吱游戏</a><br/>
							下载吱吱示例： <a href="http://www.zizi.im/zizi/down/demo_1.20.zip" target="_blank">demo</a></p>
								
							
						</div>

						
					</div>
				
					<!--  -->
					<div class="sub_container2 right">
						
						<div class="tit_2">
							>第一步：建立游戏
						</div>
						<div class="question">
							建立游戏后，将获得这个游戏的游戏id,类似下面的一长串字符。</br>
							例如：<span class="red1" id="backGameid3">BFD6720336116352094CD94EB86C****</span>
						</div>
						<div class="tit_2">
							>第二步：嵌入吱吱代码
						</div>
						<div class="question">
							<span class="blue1"><b>导入吱吱sdk：</b></span><br/>
							请参考示例，将ruiji_zhizhi.swc导入游戏项目<br/>

							<span class="blue1"><b>导入吱吱UI：</b></span><br/>
							请参考示例，将UI.swc导入游戏项目<br/>

							<span class="blue1"><b>初始化吱吱：</b></span><br/>
							请参考示例，使用initSys()方法,初始化吱吱
						</div>

						<div class="tit_2">
							>第三步：提交游戏文件
						</div>
						<div class="question">
							<span class="green1">10M以下的单文件游戏，直接上传游戏文件即可。</span><br/>
							<span class="red1">大于10M的游戏，或者多文件游戏，请联系吱吱支持人员。</span><br/>
							<b>注意：</b>
							<p>请确认在嵌入吱吱代码时，<br/>
							--参数中写入了正确的游戏id，<br/>
							--参数中写入了正确游戏高宽，<br/>
							--参数中写入了正确的吱吱界面布局坐标(在符合用户体验与方便玩家操作的标准下)</p>
						</div>
						
					</div>

				</div>

			</div>
			<!--  -->


		</div>

<%@ include file="../inc/footer.jsp" %>