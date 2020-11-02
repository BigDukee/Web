<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式</title>
</head>
<body>
<%--
    EL表达式
    作用：
        简化JSP代码
        格式：
            ${域对象的名称}
        操作对象：
            EL表达式一般操作的是域对象，不能操作局部变量
        操作范围：
            page范围
                在当期页面
            request范围
                在一次请求
            session范围
                在一次会话
            application范围
                在整个应用程序
        1.如果EL表达式获取域对象的值为空，默认显示空字符串
        2.EL表达式默认从小到大范围去找，找到为止，如果四个范围都未找到，则显示空字符串
        查找数据可以使用四个域对象对应的控件对象，分别是：
        pageScope, requestScope, sessionScope, applicationScope

--%>
<%--设置数据--%>
    <%
        pageContext.setAttribute("uname","zhangsan1");
        request.setAttribute("uname","zhangsan2");
        session.setAttribute("uname","zhangsan3");
        application.setAttribute("uname","zhangsan4");

        //定于局部变量
        String str = "hello";
    %>
<%--获取数据--%>
    获取局部变量：${str} <br>
    获取域对象：${uname}
    获取指定范围的域对象：<br>
    &nbsp;&nbsp;page范围：${pageScope.uname}<br>
    &nbsp;&nbsp;page范围：${requestScope.uname}<br>
    &nbsp;&nbsp;page范围：${sessionScope.uname}<br>
    &nbsp;&nbsp;page范围：${applicationScope.uname}<br>


</body>
</html>
