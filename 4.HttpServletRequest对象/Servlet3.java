package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发跳转
 * req.getRequestDispatcher(url).forward(req, resp);
 * 可以让请求从服务器端跳转到客户端（或跳转到指定Servlet）
 *
 * 特点：
 * 1.服务端行为
 * 2.地址栏不发生改变
 * 3.从始至终只有一个请求
 * 4.request数据可以共享
 */
@WebServlet("/ser3")
public class Servlet3 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受客户端请求的参数
        String uname = req.getParameter("uname");
        System.out.println("Servlet3 uname : " + uname);

        //请求转发跳转到Servlet4
//        req.getRequestDispatcher("ser4").forward(req, resp);    //相当于传到ser4中的req，resp

        //请求转发跳转到jsp页面
//        req.getRequestDispatcher("login.jsp").forward(req, resp);

        //请求转发跳转到html页面
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
}
