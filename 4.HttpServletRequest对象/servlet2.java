package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 乱码原因：
 * 由于在解析过程中默认使用的编码方式不支持中文，所以解析式一定会出现乱码
 *
 * 请求乱码问题
 * GET请求 Tomcat8以上不会乱码
 * POST请求 会乱码，通过设置服务器解析编码格式
 *req.setCharacterEncoding("UTF-8")     只能针对POST请求，POST请求才有效
 *
 * 如果tomcat版本小于等于7，需要借助String对象的方法，对任何请求都有效，通用
 */
@WebServlet("/ser2")
public class servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        //req.setCharacterEncoding("UTF-8");

        //获取客户端传递的参数
        String uname = req.getParameter("uname");
        String upwd = req.getParameter("upwd");
        System.out.println("姓名：" + uname);
        System.out.println("姓名：" + upwd);

        //解决tomcat7及一下版本的GET请求编码，不能随便乱用
        String name = new String(req.getParameter("uname").getBytes("ISO-8859-1"),"UTF-8")
        System.out.println("姓名：" + name);
    }
}
