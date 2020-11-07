package com.xxxx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户登录");
        //获取参数
        String uname = req.getParameter("uname");
        System.out.println(req.getParameter("uname"));

        //如果是admin表示登陆成功
        if ("admin".equals(uname)) {
            //存session
            req.getSession().setAttribute("user",uname);
            //跳转到首页
            resp.sendRedirect("index.jsp");
        }else {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
