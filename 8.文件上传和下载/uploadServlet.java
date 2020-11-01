package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 文件上传
 * 使用注解@MultipartConfig将一个Servlet标识为支持文件上传。
 * Servlet将multiparty/form-data的POST请求封装成Part对象，通过Part对上传的文件进行操作。
 */
@WebServlet("/uploadServlet")
@MultipartConfig
public class uploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        System.out.println("文件上传");

        //设置请求的编码格式
        req.setCharacterEncoding("UTF-8");

        //获取普通表单项（获取参数）
        String uname = req.getParameter("uname");    //表单中表单元素的uname属性值
        System.out.println("uname: " + uname);

        //获取part对象（Servlet将multiparty/form-data的POST请求封装成Part对象）
        Part part = req.getPart("myfile");      //表单中文件域的name属性值

        //通过part对象得到上传的文件名
        String filename = part.getSubmittedFileName();
        System.out.println("上传文件名：" + filename);

        //得到文件存放的路径
        String filePath = req.getServletContext().getRealPath("/");
        System.out.println("文件存放的路径：" + filePath);

        //上传文件到指定目录
        part.write(filePath + "/" + filename);
    }
}
