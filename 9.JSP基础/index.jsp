<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆</title>
</head>
<body>
    <h2>欢迎<%=session.getAttribute("uname")%>登录！</h2>
</body>
</html>
