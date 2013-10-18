<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/nav.jsp"%>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="applyNew" method="post" enctype ="multipart/form-data">
    <input type="hidden" name="id" value="${gameid}" />
    <table cellpadding=0 cellspacing=0 style="margin: auto;">
        <tr>
            <td style="height:30px;">商店名称:</td><td><input type="text" size="31" name="name"/></td>
      	</tr>
      	<tr>
      		<td>可否售卖道具</td><td>
      		<select name="canrecycle">
      		<option value="1">允许</option>
      		<option value="0">禁止</option>
      		</select>
      		</td>
		</tr>
      	<tr>
      		<td>折扣:</td><td><input type="text" name="recyclerate" value="0.2" /></td>
		</tr>
      	<!--tr>
      		<td>商店对应swf:</td><td><input type="file" size="31" name="swfurl"/></td>
		</tr-->
		<tr>
			<td colspan="2" align="center">
                <input type="submit" value="添加" />
            </td>
        </tr>
    </table>
</form>
<%@ include file="../inc/footer.jsp"%>