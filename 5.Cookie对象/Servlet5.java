package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie的路径问题
 * 当前服务器下任何项目的任意资源都可获取cookie对象
 * 当前项目下的资源可获取cookie对象（默认不设置cookie的path）
 * 指定项目下的资源可获取cookie对象
 * 指定目录下的资源可获取cookie对象
 */
@WebServlet("/ser5")
public class Servlet5 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //当前服务器下任何项目的任意资源都可获取cookie对象
        Cookie cookie1 = new Cookie("cookie1","cookie2");
        //设置路径为"/"，表示在当前服务器下任何项目都可以访问到Cookie对象
        cookie1.setPath("/");
        resp.addCookie(cookie1);

        //当前项目下的资源可获取cookie对象（默认不设置cookie的path）
        Cookie cookie2 = new Cookie("cookie2","cookie2");
        //设置不设置cookie的path或者设置为当前站点名
        cookie2.setPath("/sc04");
        resp.addCookie(cookie2);

        //指定项目下的资源可获取cookie对象
        Cookie cookie3 = new Cookie("cookie3","cookie3");
        //设置指定项目的站点名
        cookie3.setPath("/sr03");
        resp.addCookie(cookie3);

        //指定目录下的资源可获取cookie对象
        Cookie cookie4 = new Cookie("cookie4","cookie4");
        //设置指定项目的站点名
        cookie4.setPath("/sc04/ser3");
        resp.addCookie(cookie4);

    }
}
