package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 字符流响应数据
 * 响应中文必定会乱码
 * 解决方法：
 * 第一步，设置服务端的编码格式
 * resp.setContentType("UTF-8");
 * 第二部，设置客户端的编码格式
 * resp.setHeader("content-type","text/html;charset=UTF-8")
 *
 * 以上，需要设置客户端和服务端的编码都支持中文，并保持一致
 * ---------------------------------------------------------------
 * 同时设置客户端和服务端的编码格式
 * resp.setContentType("text/html;charset=UTF-8")
 *
 */
@WebServlet("/ser8")
public class servlet8 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        //设置服务端的编码格式
//        resp.setCharacterEncoding("UTF-8");
//        //设置客户端的编码格式和响应的MIME类型
//        resp.setHeader("content-type","text/html;charset=UTF-8");

        //同时设置客户端和服务端的编码格式
        resp.setContentType("text/html;charset=UTF-8");

        //getWrite（）
        //获取字符输出流
        PrintWriter writer = resp.getWriter();
        //输出数据
        writer.write("<h2>你好</h2>");
    }
}
