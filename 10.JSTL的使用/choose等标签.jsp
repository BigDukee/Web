<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/3
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>choose，when和otherwise标签</title>
</head>
<body>
<%--
    choose，when和otherwise标签
        <c:choose>
            <c:when test="<boolean>">
            ...
            </c:when>
            ...
            ...
            <c:otherwise>
            ...
            </c:otherwise>
        </c:chhose>
    属性：
        1.choose标签没有属性
        2.when标签只有一个test属性，必须属性
        3.otherwise标签没有属性
    注意：
        1.choose和otherwise标签没有属性，而when标签必须要有一个test属性
        2.choose标签中必须包含至少一个when标签，可以没有otherwise标签
        3.otherwise必须设置在最后一个when标签之后
        4.choose标签中只能设置when标签与otherwise标签
        5.when标签和otherwise标签中可以嵌套其他标签
        6.otherwise标签会在所有的when标签不执行是才执行

--%>

<%
    request.setAttribute("score",80);
%>
<c:choose>
    <c:when test="${score<60}">
        <h2>不及格</h2>
    </c:when>
    <c:when test="${score==60}">
        <h2>哈哈</h2>
        <c:if test="${1==1}">111</c:if>
    </c:when>
    <c:when test="${score>60 && score<80}">
        <h2>....</h2>
    </c:when>
    <c:otherwise>
        <h2>6666</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
