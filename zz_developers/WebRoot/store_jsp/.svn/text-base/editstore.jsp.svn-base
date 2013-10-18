<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/nav.jsp"%>
<%@page import="com.qkzz.web.developer.bean.Store"%>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="/store/edit" method="post" enctype ="multipart/form-data">
    <input type="hidden" name="id" value="${storebean.id}" />
    <input type="hidden" name="gameid" value="${storebean.gameid}" />
    <table cellpadding=0 cellspacing=0 style="margin: auto;">
        <tr>
            <td style="height:30px;">商店名称：</td>
            <td><input type="text" size="31" name="name" value="${storebean.name}"/></td>
      	</tr>
      	<% Store store = (Store)request.getAttribute("storebean"); %>
      	<tr>
      		<td>可否售卖道具</td><td>
      		<select name="canrecycle">
      		<option value="1"<%if(store.getCanrecycle()==1){%> selected<%}%>>允许</option>
      		<option value="0"<%if(store.getCanrecycle()==0){%> selected<%}%>>禁止</option>
      		</select>
      		</td>
		</tr>
      	<tr>
      		<td>折扣:</td><td><input type="text" name="recyclerate" value="${storebean.recyclerate}" /></td>
		</tr>
      	<!--tr>
      		<td>商店swf：</td>
      		<td><input type="file" size="31" name="swfurl"/><br/>${storebean.swfurl}<br/></td>
      	</tr-->
      	<tr>
      		<td colspan="2" align="center"><input type="submit" value="提交" /> <input type="button" onclick="history.back()" value="返回" /></td>
        </tr>
    </table>
</form>
<%@ include file="../inc/footer.jsp"%>