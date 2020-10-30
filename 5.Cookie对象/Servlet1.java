package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie的创建和发送
 */
@WebServlet("/ser1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Cookie的创建
        Cookie cookie = new Cookie("name","admin");

        //发送（响应）Cookie对象
        resp.addCookie(cookie);

    }
}
