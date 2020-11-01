package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * session 的销毁
 * 1.默认到期时间    30min
 * 2.手动设置到期时间
 * 3.立即销毁
 * 4.关闭浏览器失效 （session底层依赖cookie，cookie对象默认只在浏览器内存中存活，关闭浏览器则失效）
 * 5.关闭服务器失效
 */
@WebServlet("/ser3")
public class session3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session对象
        //获取session对象
        HttpSession session = req.getSession();

        //设置session域对象
        session.setAttribute("uname","admin");

//        //获取session对象的最大不活动时间
//        System.out.println("time: " + session.getMaxInactiveInterval());
//        //修改最大不活动时间
//        session.setMaxInactiveInterval(15);     //15秒失效

//        //立即销毁
//        session.invalidate();

    }
}
