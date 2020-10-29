package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * request作用域
 * 通过该对象可以在一个请求中传递数据，作用范围：在一次请求中有效，即服务器跳转有效
 */
@WebServlet("/ser5")
public class Servlet5 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet5");

        //设置域对象内容,任意类型
        req.setAttribute("name","admin");
        req.setAttribute("age",10);
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("efg");
        req.setAttribute("list",list);

        //请求转发跳转到Ser6
        //req.getRequestDispatcher("ser6").forward(req, resp);

        //请求转发跳转到jsp，并通过域对象传递数据
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
