package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向与请求转发的区别
 * 请求转发的地址栏不会发生改变，重定向的地址栏会发生改变
 * 请求转发只有一次请求，重定向有两次请求
 * 请求转发是request对象可共享，重定向时request对象不可共享
 * 请求转发是服务端行为，重定向是客户端行为
 * 请求转发时的地址只能时当前站点下的资源（当前项目），重定向时地址可以是任何地址
 */
@WebServlet("/ser12")
public class servlet12 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet12");

        //接收参数
        String uname = req.getParameter("uname");
        System.out.println("servlet11" + uname);

        //设置request域对象
        req.setAttribute("upwd","1234");

//        //请求转发
//        req.getRequestDispatcher("index1.jsp").forward(req, resp);

//        //重定向
//        resp.sendRedirect("index1.jsp");

//        //重定向跳转到百度
//        resp.sendRedirect("http://www.baidu.com");

//        //请求转发到百度   (404)
//        req.getRequestDispatcher("http://www.baidu.com").forward(req,resp);


    }
}
