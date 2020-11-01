package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * session 域对象
 *setAttribute()设置域对象
 * getAttribute()设置域对象
 * removeAttribute()移除域对象
 *
 * 请求转发，一次请求,request作用域是有效的，session作用域有效
 * 重定向，两次请求，request作用域无效，session有效
 */
@WebServlet("/ser2")
public class session2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session对象
        //获取session对象
        HttpSession session = req.getSession();

        //设置session域对象
        session.setAttribute("uname","admin");
        session.setAttribute("uname1","admin1");

        //移除session域对象
        session.removeAttribute("uname");

        //request域对象
        req.setAttribute("name","admin2");

//        //请求转发跳转到jsp页面
//        req.getRequestDispatcher("index.jsp").forward(req, resp);

        //重定向调转到index.jsp页面
        resp.sendRedirect("index.jsp");
    }
}
