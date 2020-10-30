package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie的获取
 * 返回的是cookie数组
 */
@WebServlet("/ser2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cookie数组
        Cookie[] cookies = req.getCookies();

        //判断cookie是否为空
        if (cookies != null && cookies.length > 0) {
            //遍历cookie数组
            for (Cookie cookie : cookies) {
                //获取cookie的名称和值
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("名称：" + name + "，值：" + value);
            }
        }

    }
}
