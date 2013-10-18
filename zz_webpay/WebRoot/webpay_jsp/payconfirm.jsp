<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="com.qkban.alipay.Payment"%>
<%
	String ERR_MSG = (String)request.getAttribute("ERR_MSG");

	if(ERR_MSG != null && !ERR_MSG.equals("")) {
		String warn = "";
		if(ERR_MSG.equals("UID_EMPTY")) {
			warn = "用户ID不能为空，请返回重新填写！";
		} else if(ERR_MSG.equals("UID_NOT_EQUAL")) {
			warn = "您输入的ID与确认ID不符，请返回重新填写！";
		} else if(ERR_MSG.equals("AMOUNT_EMPTY")) {
			warn = "充值金额不能空，请返回重新填写！";
		} else if(ERR_MSG.equals("AMOUNT_FORMAT_WRONG")) {
			warn = "充值金额格式不正确，请返回重新填写！";
		} else if(ERR_MSG.equals("USER_NOT_EXIST")) {
			warn = "您输入的充值账户不存在，请返回重新填写！";
		}
		
		%><script>alert("<%=warn%>")</script><%
		%><script>history.back(1)</script><%
		return;
	}
	
	
	String paygateway		= "https://www.alipay.com/cooperate/gateway.do?";	//支付接口
	String service      	= "create_direct_pay_by_user";						//快速付款交易服务
	String sign_type       	= "MD5";
	String out_trade_no		= (String)request.getAttribute("indentnumber");		//商户网站订单
	String input_charset   	= "utf-8";       
	String partner			= "2088002096999840"; 								//支付宝合作伙伴id (账户内提取)
	String key             	= "6wxi30wlrrcp5vwo6skt55yss830lu8v"; 				//支付宝安全校验码(账户内提取)
	String body				= (String)request.getAttribute("productTitle"); 	//商品描述，推荐格式：商品名称（订单编号：订单编号）
	String total_fee		= (String)request.getAttribute("money");			//订单总价
	String payment_type     = "1";												//支付宝类型.1代表商品购买
	String seller_email		= "leawaki@163.com";		 						//卖家支付宝帐户
	String subject			= (String)request.getAttribute("productTitle");		//商品名称
	String show_url        	= "www.wangxing365.com";
	String notify_url		= "http://pay.7kban.com/alipayNotify";		//通知接收URL
	String return_url		= "http://pay.7kban.com/alipayReturn";		//支付完成后跳转返回的网址URL
	//********************************************
	String paymethod 		= "";	//赋值:bankPay(网银);cartoon(卡通); directPay(余额)
                      				//三种付款方式都要，参数为空
	String defaultbank = "ICBCB2C";//ICBCB2C	中国工商银行
									//CMB		招商银行
									//CCB		中国建设银行
									//ABC		中国农业银行
									//SPDB		上海浦东发展银行
									//SPDBB2B	上海浦东发展银行(B2B)
									//CIB		兴业银行
									//GDB		广东发展银行
									//SDB		深圳发展银行
									//CMBC		中国民生银行
									//COMM		交通银行
									//POSTGC	邮政储蓄银行
									//CITIC		中信银行
									//CCBVISA	建行VISA
									//VISA		VISA

	String ItemUrl = Payment.CreateUrl(paygateway,service,sign_type,out_trade_no,input_charset,partner,key,show_url,body,total_fee,payment_type,seller_email,subject,notify_url,return_url,paymethod,defaultbank);

%><%@ include file='/common/header.jsp'%>
	<link rel="stylesheet" type="text/css" href="http://wangxing365.com/css/pay.main.ali.css">
	<script type="text/javascript" src="http://wangxing365.com/js/ajax.js"></script>
	<script type="text/javascript" src="http://wangxing365.com/js/jquery_002.js"></script>
	<script type="text/javascript">JQ = $;</script>
	<script type="text/javascript" src="http://wangxing365.com/js/jquery_004.js"></script>
	<script type="text/javascript">
		JQ(document).ready(function() {
			//用于控制显示哪个TAB
			var isPayMethodNotEmpty = true;
			//常量
			var pay_value = 'netPay'
			var tabControl = "";
		
			if(isPayMethodNotEmpty){
				tabControl = 'netPay';
			} 
        
			if(tabControl==pay_value){
				JQ('#container-1').tabs(2);
			}else {
				JQ('#container-1').tabs(1);
			}
		});

		var paypwdcheck = false;
		function queryPayPwd(){
			var payPwd = document.accountpaysubmit.paypwd.value;
		    var url="/paypwddata.do";
		    var data="pwd="+payPwd;
		    asynSubmit(url,data,"GET",paypwdResult);
			return true;
		}
		
		function paypwdResult(){
		  if(xmlHttp.readyState==4){
		    var response=xmlHttp.responseText;
		    if(xmlHttp.status==200){
		    	if(response == "false") {
		    		paypwdcheck = false;
					document.getElementById("warntips").innerHTML = "您的支付密码不正确，请确认！";
					document.getElementById("warntips").style.display="";
				} else {
					paypwdcheck = true;
					document.getElementById("warntips").style.display="none";
					
				}
		    }
		  }
		}
		
		function paypwdValidate() {
			var b = queryPayPwd();
			//alert("b:"+b+"====paycheck:"+paypwdcheck);
			if(b==true && paypwdcheck == true) {
				//alert("aaaaaa");
				document.accountpaysubmit.submit();
				return true;
			}
			//alert("bbb");
			return false;
		}
		
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
					<form name="alipaysubmit" method="post" action="https://www.alipay.com/cooperate/gateway.do?_input_charset=utf-8">		 
					<div class="TabContent clearfix">
						<dl>
							<dt>充值账户：<span style="color:red;font-weight:bold"><%=request.getAttribute("userid") %>&nbsp;</span></dt>
							<dt>充值金额：<span style="color:red;font-weight:bold"><%=request.getAttribute("money") %>&nbsp;</span>元</dt>
							<dd>
								<div class="warning">
									<img src="http://wangxing365.com/img/warning.gif" /> 您本次充值所产生的订单号是：<span style="color:red;font-weight:bold;"><%=out_trade_no %></span>，订单号用于支付完成后的事件查询及便于7kban迅速处理问题，您可在自己的"我的7kban"中查看"充值明细"，查看具体充值情况。<br/>
									<br/><span style="color:red;font-weight:bold;">温馨提示：<br/>付款成功后，请等待网页自动跳转返回7kban在线充值成功提示页面，尽量不要手动关闭网页哦。</span>
								</div>
							</dd>
			                <dd>
			                	<div id="comfirm1" class="Pad">
			                		<!--button 7 start-->
			                		<button class="payButton WordSeven" type="submit" onClick="document.alipaysubmit.submit()">确认无误，付款</button>
			                		<!--button 7 ending-->
			                	</div>
		    	            </dd>
						</dl>
					</div>
				 	<input type=hidden name="body" value="<%=body%>">
				 	<input type=hidden name="notify_url" value="<%=notify_url%>">
					<input type=hidden name="out_trade_no" value="<%=out_trade_no%>">
					<input type=hidden name="partner" value="<%=partner%>">
					<input type=hidden name="payment_type" value="<%=payment_type%>"> 
					<input type=hidden name="seller_email" value="<%=seller_email%>">
					<input type=hidden name="service" value="<%=service%>">
					<input type=hidden name="sign" value="<%=ItemUrl%>"> 
					<input type=hidden name="sign_type" value="MD5">      
					<input type=hidden name="subject" value="<%=subject%>">
					<input type=hidden name="total_fee" value="<%=total_fee%>">
					<input type=hidden name="show_url" value="<%=show_url%>">
					<input type=hidden name="return_url" value="<%=return_url%>">
					<input type=hidden name="paymethod" value="<%=paymethod%>">
					<input type=hidden name="defaultbank" value="<%=defaultbank%>">
					</form>
				</div>
			</div>
		</div>
	
	</div>



<%@ include file='/common/footer.jsp'%>
