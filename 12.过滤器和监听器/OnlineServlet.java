package com.xxxx.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/online")
public class OnlineServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建session对象
        HttpSession session = req.getSession();


        //获取参数值 key
        String key = req.getParameter("key");
        //判断参数是否为空，如果不为空，则表示做退出操作
        if (key != null && "logout".equals(key)) {
            //销毁session对象
            session.invalidate();
            return;
        }


        /**
         * 登录操作
         */

        //得到当前作用域中的在线人数
        Integer onlineNumber = (Integer) session.getServletContext().getAttribute("onlineNumber");

        //设置响应类型及编码
        resp.setContentType("text/html;charset=UTF-8");

        //输出人数
        resp.getWriter().write("<h2>当前在线人数：" + onlineNumber + "</h2> <a href='online?key=logout'>退出</a>");
    }
}
