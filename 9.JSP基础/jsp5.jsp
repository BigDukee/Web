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
        page作用域
            在当前页面有效，跳转后无效
        request作用域
            在一次请求中有效，服务端跳转有效，客户端跳转无效
        session作用域
            在一次会话中有效，服务端和客户端跳转有效
        application作用域
            在整个应用中有效

        JSP中跳转方式
        1.服务端跳转
        <jsp:forward page="跳转的页面地址"></jsp:forward>
        2.客户端跳转
        超链接

--%>
<%
    //设置page范围的域对象
    pageContext.setAttribute("name1","zhangsan1");

    //设置request范围的域对象
    request.setAttribute("name2","zhangsan2");

    //设置session范围的域对象
    session.setAttribute("name3","zhangsan3");

    //设置application范围的域对象
    application.setAttribute("name4","zhangsan4");
%>
<%--jsp中服务端跳转--%>
<%--<jsp:forward page="jsp5.1.jsp"></jsp:forward>--%>

<%--超链接跳转--%>
<a href="jsp5.1.jsp">跳转</a>

</body>
</html>
