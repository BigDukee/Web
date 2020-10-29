<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/10/29
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h2>index 页面</h2>
<%--如果要在jsp中写Java代码，需要写在脚本段中--%>
<%
    // 获取域对象
    // 获取对象内容
    // 获取对象内容
    String name = (String) request.getAttribute("name");
    System.out.println("name: " + name);
    Integer age = (Integer) request.getAttribute("age");
    System.out.println("age: " + age);
    List<String> list = (List<String>) request.getAttribute("list");
    System.out.println(list.get(0));
%>
</body>
</html>
