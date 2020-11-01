<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/10/31
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取域对象</title>
</head>
<body>
<%
    //获取session域对象
    String uname = (String) request.getSession().getAttribute("uname");
    String uname1 = (String) request.getSession().getAttribute("uname1");

    //获取request对象
    String name = (String) request.getAttribute("name");

    out.print("uname: " + uname);
    out.print("uname1: " + uname1);
    out.print("name: " + name);
%>

</body>
</html>
