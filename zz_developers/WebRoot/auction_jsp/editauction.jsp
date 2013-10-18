<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN">
<head><title>${pageTitle}</title>
<link href="../inc/default.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%} %>
<form action="/auction/edit" method="post" enctype ="multipart/form-data">
    <input type="hidden" name="id" value="${auctionbean.id}" />
    <input type="hidden" name="gameid" value="${auctionbean.gameid}" />
    <table width="90%" cellpadding=0 cellspacing=0 style="margin: auto;">
        <tr>
            <td style="height:30px;" colspan="5">
                <input type="text" size="31" name="name" value="${auctionbean.name}"/><br/>
                <!--input type="file" size="31" name="swfurl"/>${auctionbean.swfurl}<br/-->
                <input type="submit" value="添加" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>