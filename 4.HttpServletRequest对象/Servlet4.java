package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发跳转
 * 可以让请求从服务器端跳转到客户端（或跳转到指定Servlet）
 * 服务端行为
 */
@WebServlet("/ser4")
public class Servlet4 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受客户端请求的参数
        String uname = req.getParameter("uname");
        System.out.println("Servlet4 uname : " + uname);
    }
}
