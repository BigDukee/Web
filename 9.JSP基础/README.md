# JSP简介

## 什么是Java Server Pages?

JSP全称Java Server Pages，是一种动态网页开发技术。它使用JSP标签在HTML网页中插入Java代码。标签通常以<%开头以%>结束。

JSP是一种Java servlet，主要用于实现Java web应用程序的用户界面部分。网页开发者们通过结合HTML代码、XHTML代码、XML元素以及嵌入JSP操作和命令来编写JSP。

JSP通过网页表单获取用户输入数据、访问数据库及其他数据源，然后动态地创建网页。

JSP标签有多种功能，比如访问数据库、记录用户选择信息、访问JavaBeans组件等，还可以在不同的网页中传递控制信息和共享信息。



------

## 为什么使用JSP？

JSP程序与CGI程序有着相似的功能，但和CGI程序相比，JSP程序有如下优势：

- 性能更加优越，因为JSP可以直接在HTML网页中动态嵌入元素而不需要单独引用CGI文件。
- 服务器调用的是已经编译好的JSP文件，而不像CGI/Perl那样必须先载入解释器和目标脚本。
- JSP 基于Java Servlet API，因此，JSP拥有各种强大的企业级Java API，包括JDBC，JNDI，EJB，JAXP等等。
- JSP页面可以与处理业务逻辑的 Servlet 一起使用，这种模式被Java servlet 模板引擎所支持。

最后，JSP是Java EE不可或缺的一部分，是一个完整的企业级应用平台。这意味着JSP可以用最简单的方式来实现最复杂的应用。

------

## JSP的优势

以下列出了使用JSP带来的其他好处：

- 与ASP相比：JSP有两大优势。首先，动态部分用Java编写，而不是VB或其他MS专用语言，所以更加强大与易用。第二点就是JSP易于移植到非MS平台上。
- 与纯 Servlet 相比：JSP可以很方便的编写或者修改HTML网页而不用去面对大量的println语句。
- 与SSI相比：SSI无法使用表单数据、无法进行数据库链接。
- 与JavaScript相比：虽然JavaScript可以在客户端动态生成HTML，但是很难与服务器交互，因此不能提供复杂的服务，比如访问数据库和图像处理等等。
- 与静态HTML相比：静态HTML不包含动态信息。

------



## JSP 处理

以下步骤表明了 Web 服务器是如何使用JSP来创建网页的：

- 就像其他普通的网页一样，您的浏览器发送一个 HTTP 请求给服务器。
- Web 服务器识别出这是一个对 JSP 网页的请求，并且将该请求传递给 JSP 引擎。通过使用 URL或者 .jsp 文件来完成。
- JSP 引擎从磁盘中载入 JSP 文件，然后将它们转化为 Servlet。这种转化只是简单地将所有模板文本改用 println() 语句，并且将所有的 JSP 元素转化成 Java 代码。
- JSP 引擎将 Servlet 编译成可执行类，并且将原始请求传递给 Servlet 引擎。
- Web 服务器的某组件将会调用 Servlet 引擎，然后载入并执行 Servlet 类。在执行过程中，Servlet 产生 HTML 格式的输出并将其内嵌于 HTTP response 中上交给 Web 服务器。
- Web 服务器以静态 HTML 网页的形式将 HTTP response 返回到您的浏览器中。
- 最终，Web 浏览器处理 HTTP response 中动态产生的HTML网页，就好像在处理静态网页一样。

------



# JSP 生命周期

理解JSP底层功能的关键就是去理解它们所遵守的生命周期。

JSP生命周期就是从创建到销毁的整个过程，类似于servlet生命周期，区别在于JSP生命周期还包括将JSP文件编译成servlet。

以下是JSP生命周期中所走过的几个阶段：

- **编译阶段：**

  servlet容器编译servlet源文件，生成servlet类

- **初始化阶段：**

  加载与JSP对应的servlet类，创建其实例，并调用它的初始化方法

- **执行阶段：**

  调用与JSP对应的servlet实例的服务方法

- **销毁阶段：**

  调用与JSP对应的servlet实例的销毁方法，然后销毁servlet实例



# JSP注释格式

　　**1、前端相关**

　　　　1）HTLM格式注释： <!-- 注释内容 -->（多行注释）

　　　　2）CSS格式注释：   /* 注释内容  */（多行注释）

　　　　3）JavaScript注释： /* 注释内容  */  （多行注释）、 // 注释内容（单行注释）

　　**2、Java语言**

　　　　1）单行注释：  //  注释内容

　　　　2）多行注释：  /* 注释内容  */ 

　　　　3）文档注释： JSP中不存在Java的文档注释

　　**3、JSP特有注释**

　　　　1）多行注释：<%--  注释内容 --%>

------



# Scriptlet

在JSP中最重要的部分就是Scriptlet（脚本小程序），所有嵌入在HTML代码中的Java程序。

在JSP中一共有三种Scriptlet代码：都必须使用Scriptlet标记出来

```Java
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
```



```Java
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
```



# JSP的指令标签

使用包含操作，可以将一些重复的代码包含进来使用，从正常的页面组成来看，有时可能分为几个区域。而其中的一些区域可能是一直不需要改变的，改变的就其中的一个具体内容区域。现在有两种方法可以实现上述功能。

方法一：在每个JSP页面（HTML）都包含工具栏，头部信息，尾部信息，具体内容

方法二：将工具栏，头部信息，尾部信息都分成各个独立的文件，使用的时候直接导入。

很明显，第二种方法比第一种更好，第一种会存在很多重复的代码，并且修改很不方便，在JSP中如果想实现包含操作，有两种做法：静态包含和动态包含，静态包含使用include指令即可，动态包含则需要使用include动作标签。



## include静态包含

```Java
		<%@include file="要包含的页面地址"%>
```

静态包含就是将内容进行了直接的替换，就好比程序中的定义的变量一样，是在servlet引擎转译时，就把此文件的内容包含了进去（两个文件的源代码整合到一起，全部放到_jspService中），所以**只生成了一个servlet**，所以两个页面不能有同名的变量。运行效率高一点点。耦合度较高，不够灵活。

```Java
    <%--
        include静态包含
            格式：<%@include file="要包含的页面地址"%>
            特点：
                1.将内容进行了直接的替换
                2.静态包含只会生成一个源码文件，最终的内容全部在_jspService方法体中（源码文件中）
                3.不能出现同名变量
                4.运行效率高一点点。耦合度较高，不够灵活。
    --%>
```



```Java
    <%@include file="jsp3.1.jsp"%>
    <h2>主体内容</h2>
    <%@include file="jsp3.2.jsp"%>
```



## include动态包含

动态包含在代码编译阶段，包含和被包含部分是两个独立的部分，只有当运行时，才会动态包含进来，好比方法的调用。



```Java
<%--
    include动态包含
        格式：<jsp:include page="要包含的页面路径"></jsp:include>
        特点：
            1.动态包含相当于方法的调用
            2.动态包含会生成多个源码文件
            3.可以定义同名变量
            4.效率高，耦合度地
        当动态包含不需要传递参数时，include双标签之间不要有任何内容，包括换行和空格

        使用动态包含传递参数
        <jsp:include page="要包含的页面路径">
            <jsp:param name="参数名" value="参数值"/>
        </jsp:include>
        name属性不支持表达式，value属性支持表达式

        获取参数：
            request.getParameter(name); 通过指定参数名获取参数值


--%>
```



```Java
<jsp:include page="jsp3.1.jsp"></jsp:include>
    <h2>主体内容</h2>
<jsp:include page="jsp3.2.jsp"></jsp:include>
<%--动态包含传递参数--%>
<jsp:include page="jsp3.2.jsp">
    <jsp:param name="uname" value="admin"/>
</jsp:include>
```





------

# JSP的四大域对象



## 四种属性的范围

在JSP中提供了四种属性的保存范围，所谓的属性保存范围，指的就是一个设置的对象，可以在多少个页面中保存并且可以继续使用

1.page范围

pageContext：只在一个页面中保存属性，跳转之后无效

2.request范围

request：之在一次请求中保存，服务器跳转后依然有效

3.session范围

session：在一次会话范围中，无论何种跳转都可以使用

4.application范围

application：在整个服务器上保存

| 方法                                           | 类型 | 描述                 |
| ---------------------------------------------- | ---- | -------------------- |
| public void setAttribute(String name,Object o) | 普通 | 设置属性的名称及内容 |
| public Object getAttribute(String name)        | 普通 | 根据属性名称取属性   |
| public void removeAttribute(String name)       | 普通 | 删除指定的属性       |



## 验证属性范围的特点 

1.page

本页面取得，服务器端跳转（<jsp:forward>）后无效

2.request

服务器跳转有效，客户端跳转无效

如果时客户端跳转，则相当于发出了两次请求，那么第一次的请求将不存在了；如果希望不管是客户端还是服务器跳转，都能保存的话，就需要继续扩大范围。

3.session

无论客户端还是服务器端都可以取得，但是现在重新打开一个新的浏览器，则无法取得之前设置的session了，因为每一个session只保存在当前的浏览器中，并在相关的页面取得。

对于服务器而言，每一个连接到他的客户端都是一个session

如果想让属性设置一次之后，不管是否是新的浏览器打开都能取得则可以使用application

4.application

所有的application属性直接保存在服务器上，所有的用户（每一个session）都可以直接访问取得

只要是通过application设置的属性，则所有的session都可以取得，表示公共的内容，但是如果此时服务器重启了，则无法取得了，因为关闭服务器后，所有的属性都消失了，所以需要重新设置。

```jsp
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

```



```jsp
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
```

