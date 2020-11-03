# JSTL



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

