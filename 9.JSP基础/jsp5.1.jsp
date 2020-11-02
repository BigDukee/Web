<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP的四大域对象</title>
</head>
<body>
<%--
    JSP的四大域对象
--%>
<%
    //获取域对象中的值
    out.print("page范围：" + pageContext.getAttribute("name1") + "<br>");
    out.print("request范围：" + request.getAttribute("name2") + "<br>");
    out.print("session范围：" + session.getAttribute("name3") + "<br>");
    out.print("application范围：" + application.getAttribute("name4") + "<br>");

%>
</body>
</html>
