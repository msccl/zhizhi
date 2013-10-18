<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN">
<head><title>${pageTitle}</title>
</head>
<body>
<%if(request.getAttribute("ERR_CODE")!=null){ %><strong>${ERR_CODE}</strong><br/><%}else{ %>成功<%} %>
</body>
</html>