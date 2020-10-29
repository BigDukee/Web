<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/10/30
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--java脚本段-->
<%
    //获取参数
    String uname = request.getParameter("uname");

    //获取request作用域
    String upwd = (String) request.getAttribute("upwd");

    //输出内容
    out.print(uname);
    out.print("--------------------");
    out.print(upwd);
%>
</body>
</html>
