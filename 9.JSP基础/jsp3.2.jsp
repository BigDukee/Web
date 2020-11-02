<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>include静态包含底部</title>
</head>
<body>
    <h2>底部内容</h2>
<%
    int a=10;
    //获取动态包含传递的参数
    String uname = request.getParameter("uname");
    out.print(uname);
%>
</body>
</html>
