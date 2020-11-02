<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="loginServlet" method="post">
    姓名：<input type="text" name="uname"> <br>
    密码：<input type="password" name="upwd"> <br>
    <button>登录</button>
    <%--获取后台设置在作用域中的数据，并显示--%>
    <%--<span style="color:red;font-size:12px"><%=request.getAttribute("msg")%></span>--%>

    <%--EL表达式--%>
    <span style="color:red;font-size:12px">${msg}</span>
</form>

</body>
</html>
