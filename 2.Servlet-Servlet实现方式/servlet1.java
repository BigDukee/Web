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
//@WebServlet("/ser1")
//@WebServlet(name="Servlet1",value = "/ser1")
    @WebServlet(name = "servlet1",urlPatterns = {"/ser1","/ser01"})
public class servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印内容在控制台
        System.out.println("Hello Servlet");
        //通过流输出数据到浏览器
        resp.getWriter().write("Hello Servlet");
    }
}
