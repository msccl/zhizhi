<%@ include file="../inc/header.jsp" %><%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/nav.jsp" %>
<div class="mainarea">
	<div class="sectitle"><strong>修改登录密码</strong><br/></div>
    <form action="/user/editpw" method="post" id="editpwd">
        <div class="content">
         <input type="hidden" name="id" value="${user.id}"/>
			安全提示问题:<br/>
			${user.pwdquestion}
			<br/>
			原安全问题答案:<br/>
			<input type="text" name="pwdanswer" value=""/>
			<br/>
			输入原密码:<br/>
			<input type="text" name="password" value=""/>
			<br/>
			输入新原密码:<br/>
			<input type="text" name="newpassword" value=""/>
         <br/>
         <input name="submit" type="submit" value="提交"/>
         <br/>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp" %>