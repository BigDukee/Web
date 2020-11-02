<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.servlet.xxx.User_JavaBean" %><%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/2
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式的使用</title>
</head>
<body>
<%--
    EL表达式的使用
        获取List
            获取List的size     ${list1.size()}
            获取List的指定下标的值   ${list1[下标]}
            list代表的是限域变量名
        获取Map
            获取Map指定key的value    ${map1.aaa}  或  ${map1["bbb"]}
            Map代表的是限域变量名
        获取JavaBean对象
            获取JavaBean中的属性
                ${javabean.属性名} 或 ${javabean对象.get属性名()}
            JavaBean中的属性字段必须提供get方法
--%>

    <%
        //List
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        request.setAttribute("list1", list);

        //Map
        Map map = new HashMap();
        map.put("aaa","111");
        map.put("bbb",222);
        map.put("ccc",333);
        request.setAttribute("map1",map);

        //JavaBean对象
        User_JavaBean user = new User_JavaBean();
        user.setUserId(1);
        user.setUname("zhangsan");
        user.setUpwd("123");
        request.setAttribute("user",user);
    %>

    <h4>获取List</h4>
    获取List的size：${list1.size()}<br>
    获取List的指定下标的值：${list1[1]}

    <h4>获取Map</h4>
    获取Map指定key的value值：${map1.aaa}   --- ${map1["bbb"]}

    <h4>获取JavaBean对象</h4>
    ${user}<br>
    获取JavaBean中的属性：${user.uname}  --- ${user.getUpwd()}



</body>
</html>
