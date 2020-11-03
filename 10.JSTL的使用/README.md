# JSTL

- [JSTL](#jstl)
  * [标签的使用](#-----)
  * [条件动作标签](#------)
    + [if标签](#if--)
    + [choose, when, otherwise 标签](#choose--when--otherwise---)
  * [迭代标签](#----)
  * [格式化动作标签](#-------)



## 标签的使用

Java Server Pages Standard Tag Library (JSTL)：JSP标准标签库，是一个定制标签类库的集合，用于解决一些常见的问题，例如迭代一个映射或集合，条件测试，XML处理，甚至数据库和访问数据库操作等。

格式

```jsp
<%@ taglib prefix="" uri="" %>
```

prefix相当于别名

核心标签

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

格式化标签

```jsp
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
```

SQL标签

```jsp
<%@ taglib prefix="sql" 
           uri="http://java.sun.com/jsp/jstl/sql" %>
```

XML标签

```jsp
<%@ taglib prefix="x" 
           uri="http://java.sun.com/jsp/jstl/xml" %>
```

JSTL函数

```jsp
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
```



## 条件动作标签

条件动作指令用于处理页面的输出结果依赖于某些输出值的情况，在Java中是利用if, if...else和switch语句来进行处理的。在JSTL中也有4个标签可以执行条件式动作指令：if, choose, when, otherwise.

### if标签

if标签先对某个条件进行测试，如果该条件运算结果为true，则处理他的主题内容，测试结果保存在一个Boolean对象中，并创建一个限域变量来引用Boolean对象。可以利用var属性设置限域变量名，利用scope属性来指定其作用范围。

```jsp
<%--
    if标签
        格式：
            <c:if test="<boolean>" var="<string>" scope="<string>">
             ...
            </c:if>
        常用属性：
            test：条件判断，操作的是域对象，接受返回结果是Boolean类型的值 （必要属性）
            var：限域变量名（存放在作用域中的变量名），用于接收判断结果的值  （可选属性）
            scope：限域变量名的范围（page,request,session,application）  （可选属性）
        1.标签操作的一般都是域对象
        2.if标签没有else，如果想要else的效果，就需要设置两个完全相反的条件

--%>
```



```jsp
<%
    //设置数据
    request.setAttribute("num",0);


%>
<c:if test="${num>0}">
    数值大于0
</c:if>
<c:if test="${num<=0}">
    数值不大于0
</c:if>

<br>
<c:if test="${num>100}" var="flag" scope="request"></c:if>
${flag}  ---  ${requestScope.flag}  ---  ${sessionScope.flag}
```



------

### choose, when, otherwise 标签

choose和when标签的作用**与Java中的switch和case关键字相似**，用于在众多选项中做出选择。也就是说：他们为相互排斥的条件式执行提供相关内容。

switch语句有case，而choose标签中对应有when，switch语句中有default，而choose标签中有otherwise。



```jsp
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
```



```jsp
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
```



------

## 迭代标签

forEach是将一个主体内容迭代多次，或者迭代一个对象的集合。可以迭代的对象包括所有的java.util.Collection和java.util.Map接口的实现，以及对象或者基本类型的数组。他还可以迭代java.util.Iterator和java.util.Enumeration，但不能在多个动作指令中使用Iterator或者Enumeration，因为Iterator或者Enumeration都不能重置（reset）。

```jsp
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
```



```jsp
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
```



------

## 格式化动作标签

JSTL提供了格式化和解析数字和日期的标签，我们讨论里面有formatNumber，formatDate，parseNumber及parseDate。

```jsp
<%--
    格式化动作标签
        formatNumber标签
            将数值型转换为=为指定格式的字符串
            语法格式：
                <fmt:formatNumber
                value="<string>"
                type="<string>"
                var="<string>"
                scope="<tring>"/>
        常用属性：
            value：要格式化的数值
            type：要格式化的类型
                number：数值型（默认）
                percent：百分比类型
                currency：货币类型
            var：限域变量名，用来接收格式化后的结果
            scope：var属性的范围（page|request|session|application）
        如果使用了var属性，标签不会输出结果，需要通过EL表达式获取
        默认的类型（type）的取值是number

    formatDate标签
        将Date型的数据转化为指定格式的字符串
        语法格式：
            <fmt:formatDate
            value="<string>"
            type="<string>"
            dateStyle="<string>"
            timeStyle="<string>"
            pattern="<string>"
            timeZone="<string>"
            var="<string>"
            scope="<string>"/>
        常用属性：
            value：要格式化的日期
            type：格式化的类型
                date：日期型 年月日
                time： 时间型 时分秒
                both：日期时间型
            dateStyle：日期格式
            timeStyle：日期时间
            pattern：自定义模式
            timeZone：时区
            var：限域变量名
            scope：

        parseNumber标签
            将指定格式的数值字符串转化成数值型
            语法格式：
                <fmt:parseNumber
                value="<string>"
                type="<string>"
                var="<string>"
                scope=="<string>" />
        parseDate标签
            将日期型的字符串转换成Date型
            <fmt:parseDate
            type="<string>"
            dateStyle="<string>"
            timeStyle="<string>"
            pattern="<string>"
            var="<string>"
            scope="<string>"

--%>
```



```jsp
<fmt:formatNumber value="10" type="number" var="num" />${num}<br>
<fmt:formatNumber value="10" type="percent" /><br>
<fmt:formatNumber value="10" type="currency" /> <br>
<%--设置时区--%>
<fmt:setLocale value="en_US" />
<fmt:formatNumber value="10" type="currency" /> <br>
<hr>

<%--格式化日期--%>
<%
    request.setAttribute("myDate",new Date());
%>
${myDate} <br>
<fmt:formatDate value="${myDate}" /> <br>
<fmt:formatDate value="${myDate}" type="date" /> <br>
<fmt:formatDate value="${myDate}" type="time" /> <br>
<fmt:formatDate value="${myDate}" type="both"/> <br>
<fmt:formatDate value="${myDate}" type="both" dateStyle="FULL"/> <br>
<fmt:formatDate value="${myDate}" type="both" timeStyle="short"/> <br>
<fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/> <br>
<hr>

<fmt:parseNumber value="100" /> <br>
<fmt:parseNumber value="100" type="number"/> <br>
<fmt:parseNumber value="100%" type="percent" /> <br>
<fmt:parseNumber value="$100.0" type="currency" /> <br>
<hr>
<fmt:parseDate value="2020/11/03" pattern="yyyy/MM/dd" /> <br>
```

