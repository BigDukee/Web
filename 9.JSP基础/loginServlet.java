package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置客户端的编码格式
        req.setCharacterEncoding("UTF-8");

        //接受客户端传递的参数
        String uname = req.getParameter("uname");
        String upwd = req.getParameter("upwd");

        //判断参数是否为空
        if (uname == null || "".equals(uname.trim())){
            //提示用户信息
            req.setAttribute("msg","用户姓名不能为空！");
            //请求转发跳转到login.jsp
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        if (upwd == null || "".equals(upwd.trim())){
            //提示用户信息
            req.setAttribute("msg","用户密码不能为空！");
            //请求转发跳转到login.jsp
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        //判断账号密码是否正确    uname=admin upwd=admin
        if (!"admin".equals(uname) || !"admin".equals(upwd)){
            //提示用户信息
            req.setAttribute("msg","登陆失败！");
            //请求转发跳转到login.jsp
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        //登录成功
        //设置登录信息到session作用域
        req.getSession().setAttribute("uname",uname);
        //跳转到index.jsp
        resp.sendRedirect("index.jsp");

    }
}
