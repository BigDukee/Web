package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字节流响应数据
 * 解决方案，同字符流一样
 */
@WebServlet("/ser9")
public class servlet9 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //同时设置客户端和服务端的编码格式
        resp.setContentType("text/html;charset=UTF-8");

        //getOutputStream()
        ServletOutputStream out = resp.getOutputStream();
        //输出数据
        out.write("<h2>你好<h2>".getBytes("UTF-8"));

    }
}
