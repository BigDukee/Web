package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie的到期时间
 * 负整数
 * 表示只在浏览器内存中存活，关闭浏览器失效（默认值为-1）
 *
 * 正整数
 * 表示存活指定秒数
 *
 * 0
 * 表示删除cookie
 */
@WebServlet("/ser3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置到期时间为负整数（默认值是-1，表示只在浏览器内存中存活，关闭浏览器失效）
        Cookie cookie = new Cookie("uname1", "zhangsan1");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);

        //设置到期时间为正整数（表示存活指定秒数，会将数据存在硬盘中）
        Cookie cookie2 = new Cookie("uname2", "zhangsan2");
        cookie2.setMaxAge(30);
        resp.addCookie(cookie2);

        //设置到期时间为0（表示删除cookie）
        Cookie cookie3 = new Cookie("uname3", "zhangsan3");
        cookie3.setMaxAge(0);
        resp.addCookie(cookie3);
    }
}
