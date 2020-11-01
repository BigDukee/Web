package com.servlet.xxx;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取servletcontext对象
 * 通过request对象获取
 * 通过session对象获取
 * 通过servletconfig对象获取
 * 直接获取
 *
 */
@WebServlet("/ser1")
public class servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取servletcontext对象
        ServletContext servletContext = req.getServletContext();

        //通过session对象获取
        ServletContext servletContext2 = req.getSession().getServletContext();

        //通过servletconfig对象获取
        ServletContext servletContext3 = getServletConfig().getServletContext();

        //直接获取
        ServletContext servletContext4 = getServletContext();

        //常用方法
        //1.获取当前服务器的版本信息
        String serverinfo = req.getServletContext().getServerInfo();
        System.out.println("获取当前服务器版本信息：" + serverinfo);
        //获取项目的真实路径
        String realPath = req.getServletContext().getRealPath("/");
        System.out.println("获取项目的真实路径： " + realPath);
    }
}
