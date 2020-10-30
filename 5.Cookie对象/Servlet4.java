package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie的注意点
 * cookie存只在当前浏览器中有效（不跨浏览器和电脑）
 * cookie不能存中文
 * 如果一定要存中文，则需要通过URLEncoder.encode编码，通过URLDecoder.decode解码
 *
 * 如果出现同名的cookie对象，则会覆盖
 *
 * cookie的存储数量是有上限的，不同浏览器的上限不通。cookie存储的大小是有限的，大概在4kb左右
 */
@WebServlet("/ser4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //cookie存中文
        String name = "姓名";
        String value = "张三";

        //将中文通过URLEncoder 进行编码
        name = URLEncoder.encode(name);
        value = URLEncoder.encode(value);

        //创建cookie对象
        Cookie cookie = new Cookie(name,value);

        //响应cookie
        resp.addCookie(cookie);

        //获取cookie时，通过URLEncoder进行解码
        Cookie[] cookies = req.getCookies();
        //判断非空
        if (cookies != null && cookies.length > 0) {
            //遍历
            for (Cookie cookiee : cookies) {
                //解码
                System.out.printf(URLDecoder.decode(cookiee.getName()));
                System.out.println(URLDecoder.decode(cookiee.getValue()));
            }
        }

        //将原来已有的cookie对象重新设置
        Cookie cookie2 = new Cookie("name","zhangsan");
        resp.addCookie(cookie2);

    }
}
