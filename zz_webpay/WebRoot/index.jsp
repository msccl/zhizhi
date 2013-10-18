<%@ page contentType="text/html;charset=utf-8" %><%
%><%@ include file='/common/header.jsp'%>
	<link rel="stylesheet" type="text/css" href="http://wangxing365.com/css/pay.main.ali.css">
	<script type="text/javascript" src="http://wangxing365.com/js/ajax.js"></script>
	<script type="text/javascript" src="http://wangxing365.com/js/jquery_002.js"></script>
	<script type="text/javascript">JQ = $;</script>
	<script type="text/javascript" src="http://wangxing365.com/js/jquery_004.js"></script>
	<script>
	<!--
	function clearNoNum(obj)
	{
		obj.value = obj.value.replace(/[^\d.]/g,"");
		obj.value = obj.value.replace(/^\./g,"");
		obj.value = obj.value.replace(/\.{2,}/g,".");
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
	
	function validate(){
		var money = document.wxsubmit.money.value;
		if(money == "") {
			document.getElementById("warntips").innerHTML = "请输入充值数额，范围在0.1至10000元";
			document.getElementById("warntips").style.display="";
			return false;
		}
		if(parseFloat(money)< 0.1 || parseFloat(money) > 10000) {
			document.getElementById("warntips").innerHTML = "充值数额超出范围，请输入0.1至10000元之间的数值";
			document.getElementById("warntips").style.display="";
			return false;
		}
		return true;
	}
	-->
	</script>
	<div class="orderPayment">
		
		<div id="Content">
			<div style="display: block;" id="container-1" class="hidden Tab">
				<ul class="anchors clearfix tabs-nav">
					<li class="tabs-selected">
						<a href="#section-2" tabindex="2" title="在线支付(网银)"><span>7kban在线充值</span></a>
					</li>
				</ul>
		
		        <div id="section-2" class="fragment tabs-container">
					<form name="wxsubmit" method="post" action="/payconfirm" onsubmit="return validate();" >		 
					<div class="TabContent clearfix">
						<dl>
							<dt>
								<div style="margin:5px 0 5px 0;"><b>您正在使用7kban支付宝充值业务</b></div>
								<div style="margin:15px 0 5px 0;">需充值的7kban账号:<input type="text" name="userid"></div>
								<div style="margin:5px 0 5px 0;">重新确认7kban账号:<input type="text" name="useridconfirm"></div> 
							</dt>
							<dt>
								请填写充值的金额：<br/>
								<span style="color:red;font-weight:bold"><input type="text" name="money" onkeyup="clearNoNum(this)"></span>元</dt>
							<dd>
								<div class="warning">
									<img src="http://wangxing365.com/img/warning.gif" /><span style="color:red;font-weight:bold;">温馨提示：<br/>付款成功后，请等待网页自动跳转返回7kban充值成功提示页面，尽量不要动手关闭网页哦。</span>
								</div>
							</dd>
			                <dd>
			                	<div id="comfirm1" class="Pad">
			                		<!--button 7 start-->
			                		<button class="payButton WordSeven" type="submit" onClick="document.wxsubmit.submit()">确认无误，付款</button>
			                		<!--button 7 ending-->
			                	</div>
		    	            </dd>
						</dl>
					</div>
					</form>
				</div>
			</div>
		</div>
	
	</div>



<%@ include file='/common/footer.jsp'%>
