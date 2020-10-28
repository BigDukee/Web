package com.qwer.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ser1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //常用方法
        //获取请求时的完整路径（从http开始，到"?"前面结束）
        String url = req.getRequestURL() + "";
        System.out.println("获取请求时的完整路径：" + url);

        //获取请求时的部分路径（从项目的站点名开始，到"?"前面结束）
        String uri = req.getRequestURI();
        System.out.println("获取请求时的部分路径：" + uri);

        //获取请求时的参数字符串（到"?"后面开始，到最后的字符串）
        String queryString = req.getQueryString();
        System.out.println("获取请求时的参数字符串：" + queryString);

        //获取请求方式（GET和POST）
        String method = req.getMethod();
        System.out.println("获取请求方式：" + method);

        //获取当前协议版本（HTTP/1.1）
        String protocol = req.getProtocol();
        System.out.println("获取当前协议版本：" + protocol);

        //获取项目的站点名（项目对外访问路径）
        String Webapp = req.getContextPath();
        System.out.println("获取项目站点名：" + Webapp);


        //获取请求的参数
        //获取指定名称的参数值,返回字符串（重点）
        String unmae = req.getParameter("uname");     //括号内的值一定要与传过来的参数保持一致
        String upwd = req.getParameter("upwd");
        System.out.println("uname:" + unmae + ", upwd：" + upwd);


        //获取指定名称的参数的所有参数值，返回字符串数组（用于复选框传值）
        String[] hobbys = req.getParameterValues("hobby");
        //判断数组是否为空
        if(hobbys != null && hobbys.length > 0){
            for(String hobby : hobbys){
                System.out.println("爱好：" + hobby);
            }
        }
    }
}
