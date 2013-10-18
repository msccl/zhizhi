<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>测试验证码</title>
<link rel="stylesheet" href="http://img.tx.com.cn/img/css/tx.wap2.0.02.css" type="text/css"/>
</head>
<body>
<div class="mainarea">
	<div class="tips">${message }</div>
   <form action="/user/valientry" method="post">
   <div class="dotline0">
			请输入验证码:<br/>
         <img src="/validate" alt="验证码B"/><br/>
         <input type="text" name="valicode" value="" style="-wap-input-format:'*N'" maxlength="4"/>
         <br/>
   </div>
   <div class="content">
      <input name="submit" type="submit" value="TEST"/><br/>
   </div>
	</form>
	<div class="content">
		<a href="/">点击这里返回首页</a>
	</div>
</div>
<style type="text/css">
<!--
body{
	background:#fff;
}
 .tag span{
	float:left;
	display:block;
	margin:0 0 8px 0;	
	padding:0 5px;
	height:20px;
	line-height:20px;
	color:#fff;
	text-decoration:none;
	margin-right:1px;
	/*font-weight:bold;*/
	/*border:solid 1px #000;*/
}
 .cs1{
	background:#db94ff;	
}
.cs2{
	background:#4c94db;	
}
.cs3{
	background:#94b770;	
}
.cs4{
	background:#ff7094;	
}
.cs5{
	background:#ff9800;
}
.cs6{
	background:#FE8B18;
}
.cs7{
	background:#FEEDCC;
}
-->
</style>
   <div class="tag">
<span class="cs7">测试</span><span class="cs1">搜搜</span><span class="cs2">星座</span><span class="cs3">忘形</span><span class="cs4">买卖</span><span class="cs5">充值</span><span class="cs6">提现</span><br/>
</div>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>