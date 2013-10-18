<%@ include file="../inc/header.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="../inc/nav.jsp" %>
<div class="sectitle"><strong>编辑道具</strong><br/></div>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="/tools/edit" method="post" enctype ="multipart/form-data">
	

	<input type="hidden" name="id" value="${toolsbean.id}" />
    <input type="hidden" name="gameid" value="${toolsbean.gameid}" />
    <table width="90%" cellpadding=0 cellspacing=0 style="margin: auto;">
        <tr>
            <td style="height:30px;" colspan="5">
                名称:<input type="text" size="31" name="name" value="${toolsbean.name}"/><br/>
                介绍:<input type="text" size="31" name="intro" value="${toolsbean.intro}"/><br/>
                是否交易:<input type="text" size="31" name="canexchange" value="${toolsbean.canexchange}"/><br/>
                是否拍卖:<input type="text" size="31" name="canauction" value="${toolsbean.canauction}"/><br/>
                功能键值:<input type="text" size="31" name="functiondefine" value="${toolsbean.functiondefine}"/><br/>
                货币ID:<input type="text" size="31" name="moneyid" value="${toolsbean.moneyid}"/><br/>
                价格:<input type="text" size="31" name="price" value="${toolsbean.price}"/><br/>
                游戏图片:<input type="file" size="31" name="image"/><br/>
              <img src="${toolsbean.img}" alt="" /><br/>
              <input type="submit" value="更新" />
            </td>
        </tr>
    </table>
</form>
<%@ include file="../inc/footer.jsp" %>