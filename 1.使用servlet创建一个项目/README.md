# 使用servlet创建一个Web项目

- [使用servlet创建一个Web项目](#--servlet----web--)
  * [1 创建一个Project](#1-----project)
  * [2.在src下创建一个包，并创建普通的Java类](#2-src-------------java-)
  * [3.实现servlet的规范，继承HttpServlet类](#3--servlet------httpservlet-)
  * [4.重写Service的规范，用来处理请求](#4--service----------)
  * [5.设置注解，指定访问的路径](#5------------)
    + [5.1WebServlet注解](#51webservlet--)
  * [6.在浏览器中启动](#6-------)
  * [代码](#--)

------



## 1 创建一个Project

## 2.在src下创建一个包，并创建普通的Java类

## 3.实现servlet的规范，继承HttpServlet类



```java
public class servlet1 extends HttpServlet
```

## 4.重写Service的规范，用来处理请求

> 在IDEA中，重写的快捷键是Ctrl+o

选择service(req:HttpServletRequest)

```Java
    //服务器创建一个servlet，调用service方法，生成Request和Response方法，来处理请求和响应
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印内容在控制台
        System.out.println("Hello Servlet");
        //通过流输出数据到浏览器
        resp.getWriter().write("Hello Servlet");
    }
```

## 5.设置注解，指定访问的路径

```Java
		@WebServlet("/ser1")	//路径中的“/”不要忘记
```

### 5.1WebServlet注解

> 其中WebServlet注解有多种写法

```Java
    String name() default "";	//名字

    String[] value() default {};	//对外访问路径

    String[] urlPatterns() default {};	//路径，过滤器拦截

```

```Java
	@WebServlet("/ser1")
	@WebServlet(name="Servlet1",value = "/ser1")
	@WebServlet(name = "servlet1",value = {"/ser1","/ser01"})   //设置多个对外访问路径
	@WebServlet(name = "servlet1",urlPatterns = {"/ser1","/ser01"})
```



## 6.在浏览器中启动

```java
http://localhost:8080/web_war_exploded/ser1
```



## 代码

```Java
package com.qwer.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现servlet
 * 1.创建普通Java类
 * 2.实现servlet的规范，继承HttpServlet
 * 3.处理Http请求，重写service方法
 * 4.设置注解，指定访问的路径
 */
@WebServlet("/ser1")
public class servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印内容在控制台
        System.out.println("Hello Servlet");
        //通过流输出数据到浏览器
        resp.getWriter().write("Hello Servlet");
    }
}
```

