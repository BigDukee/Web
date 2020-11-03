<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.servlet.xxx.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/3
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>forEach</title>
</head>
<body>
<%--
    forEach标签
        格式：
            <c:forEach
                item="<object>"
                begin="<int>"
                end="<int>"
                step="<int>"
                var="<string>"
                varStatus="<string>"
            </c:forEach>

        属性：
            begin：开始数
            end：结束数
            step：间隔数
            var：限域变量名，用来接收当前遍历的成员
            items：要循环的数据（数组，list，map等）

    forEach varStatus属性
        index：当前这次迭代从0开始的迭代索引
        count：当前这次迭代从1开始的迭代计数
        first：用来表明当前这轮迭代是否为第一次迭代的标志
        last：用来表明这轮迭代是否为最后一次迭代的标志

        1.迭代主题内容多次
        <c:forEach begin="开始数" end="结束数" step="间隔数" var="限域变量名名"
        </c:forEach>
        相当于Java中的for...int
        for(int i=0;i<10;i++){
        }
        2.循环
            <c:forEach items="要被循环的数据" var="限域变量名名"
            </c:forEach>
            相当于Java中的foreach
            for(String str : list){
            }
--%>

<%--迭代主体内容多次--%>
<c:forEach var="i" begin="1" end="10" step="2">
    ${i} &nbsp;
</c:forEach>
<hr>

<%--循环--%>
<%
    List<String> list = new ArrayList<String>();
    for (int i=1;i<=10;i++){
        list.add("A" + i);
    }
    pageContext.setAttribute("li",list);
%>
<c:forEach items="${li}" var="item">
    ${item} &nbsp;
</c:forEach>
<hr>
<table align="center" width="800" border="1" style="border-collapse: collapse;">
    <tr>
        <th>名称</th>
        <th>当前成员下标</th>
        <th>当前成员循环数</th>
        <th>是否第一次被循环</th>
        <th>是否最后一次被循环</th>
    </tr>
    <c:forEach items="${li}" var="item" varStatus="itemp">
        <tr>
            <td>${item}</td>
            <td>${itemp.index}</td>
            <td>${itemp.count}</td>
            <td>${itemp.first}</td>
            <td>${itemp.last}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<%--循环对象集合--%>
<%
    List<User> userList = new ArrayList<User>();
    User user1 = new User(1,"zhangsan1","1");
    User user2 = new User(2,"zhangsan2","12");
    User user3 = new User(3,"zhangsan3","123");
    userList.add(user1);
    userList.add(user2);
    userList.add(user3);
    //将数据设置到作用域中
    request.setAttribute("userList",userList);

%>
<%--判断集合是否为空--%>
<c:if test="${!empty userList}">
    <%--当集合不为空时遍历集合--%>
    <table align="center" width="800" border="1" style="border-collapse: collapse;">
        <tr>
            <th>用户编号</th>
            <th>用户名称</th>
            <th>用户密码</th>
            <th>用户操作</th>

        </tr>
        <c:forEach items="${userList}" var="user">
            <tr align="center">
                <td>${user.userId}</td>
                <td>${user.uname}</td>
                <td>${user.upwd}</td>
                <td><button>修改</button></td>
            </tr>
        </c:forEach>

    </table>

</c:if>
<hr>

<%--循环Map--%>
<%
    Map<String,Object> map = new HashMap<>();
    map.put("map1","aaa");
    map.put("map2","bbb");
    map.put("map3","ccc");
    pageContext.setAttribute("map",map);
%>
<c:forEach items="${map}" var="m">
    key：${m.key} &nbsp; value:${m.value} <br>
</c:forEach>


</body>
</html>
