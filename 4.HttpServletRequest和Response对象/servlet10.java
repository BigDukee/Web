package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 * 服务端指导，客户端行为
 * 存在两次请求,两次！！！数据无法共享
 * 地址栏会发生改变
 * request对象不共享
 */
@WebServlet("/ser10")
public class servlet10 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet10");

        //重定向跳转到ser11
        resp.sendRedirect("ser11");

        //接收参数
        String uname = req.getParameter("uname");
        System.out.println("servlet10" + uname);

    }
}
