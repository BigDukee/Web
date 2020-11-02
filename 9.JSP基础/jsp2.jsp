<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scriptlet脚本小程序</title>
</head>
<body>
<%--
    Scriptlet脚本小程序
        JSP中一共有三种Scriptlet脚本小程序：
            1.Java脚本段，可以写Java代码，定义局部变量，编写语句等
                <%
                    //写Java代码
                %>
                生成的代码在servlet中的service方法体中
            2.声明，声明全局变量，方法，类等
                <%!
                    //声明全局变量
                %>
                生成的代码在servlet的类体中
            3.输出表达式，可以输出变量或字面量
                <%=数值%>
                生成的代码在servlet中的service方法体中，相当于out.print()输出
--%>

<%--1.Java脚本段，可以写Java代码，定义局部变量，编写语句等--%>
<%
    //定义局部变量
    String str = "hello";
    //输出内容到控制台
    System.out.println(str);
    //输出到浏览器
    out.print(str);
    //输出全局变量
    out.write("全局变量：" + num);
%>

<%--2.声明，声明全局变量，方法，类等--%>
<%!
    //声明全局变量
    int num = 10;
%>

<%--3.输出表达式，可以输出变量或字面量--%>
<%=str%>
</body>
</html>
