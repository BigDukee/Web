package com.servlet.xxx;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件下载
 * 1.需要通过response.setContentType 方法设置Content-type头字段的值，为浏览器无法使用某种方式或激活某个程序来处理的MIME类型，
 * 例如"application/octet-stream"或"application/x-msdownload"等。
 * 2.需要通过response.setHeader方法设置Content-Disposition头的值为"attachment;filename=文件名"
 * 3.读取下载文件，调用response.getOutputStream方法向客户端写入附件内容。
 */
@WebServlet("/downloadServlet")
    public class downloadServlet extends HttpServlet {
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //
            System.out.println("文件下载");

            //设置请求的编码格式
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");

            //获取参数（得到要下载的文件名）
            String filename = req.getParameter("filename");
            //参数的非空判断   trmi()去除字符串的前后空格
            if (filename == null || "".equals(filename.trim())) {
                resp.getWriter().write("请输入要下载的文件名！");
                resp.getWriter().close();
                return;
            }

            //得到图片存放的路径
            String path = req.getServletContext().getRealPath("/download/");

            //通过路径得到file对象
            File file = new File(path + filename);

            //判断文件对象是否存在，并且是一个标准文件
            if (file.exists() && file.isFile()) {
                //设置响应类型（为浏览器无法使用某种方式或激活某个程序来处理的MIME类型）
                resp.setContentType("application/x-msdownload");
                //设置响应头
                resp.setHeader("Content-Disposition","attachment;filename" + filename);
                //响应文件，得到file文件的输入流
                InputStream in = new FileInputStream(file);
                //得到字节输出流
                ServletOutputStream out = resp.getOutputStream();
                //定义byte数组
                byte[] bytes = new byte[1024];
                //定义长度
                int len = 0;
                //循环输出
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                //关闭资源
                out.close();
                in.close();
            }else {
                resp.getWriter().write("文件不存在，请重试！");
                resp.getWriter().close();
            }
        }
}
