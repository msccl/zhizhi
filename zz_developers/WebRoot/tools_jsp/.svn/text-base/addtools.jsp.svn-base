<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN">
<head><title>${pageTitle}</title>
</head>
<body>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="add" method="post" enctype ="multipart/form-data">
    <input type="hidden" name="id" value="${gameid}" />
    <table width="90%" cellpadding=0 cellspacing=0 style="margin: auto;">
        <tr>
            <td style="height:30px;" colspan="5">
                名称:<input type="text" size="31" name="name"/><br/>
                介绍:<input type="text" size="31" name="intro"/><br/>
                是否交易:<input type="text" size="31" name="canexchange"/><br/>
                是否拍卖:<input type="text" size="31" name="canauction"/><br/>
                功能键值:<input type="text" size="31" name="functiondefine"/><br/>
                货币ID:<input type="text" size="31" name="moneyid"/><br/>
                价格:<input type="text" size="31" name="price"/><br/>
                游戏图片:<input type="file" size="31" name="image"/><br/>
                <input type="submit" value="添加" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>