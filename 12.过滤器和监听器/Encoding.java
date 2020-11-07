package com.xxxx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter("/*")
public class Encoding implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //基于HTTP
        HttpServletRequest arg0 = null;
        HttpServletRequest request = arg0;
        HttpServletResponse arg1 = null;
        HttpServletResponse response = arg1;

        //处理请求乱码（处理post请求）
        request.setCharacterEncoding("UTF-8");

        //处理get请求且服务器在tomcat8以下
        String method = request.getMethod();
        //如果是get请求
        if ("GET".equalsIgnoreCase(method)) {
            //服务器版本在tomcat8以下的apache tomcat
            String serverinfo = request.getServletContext().getServerInfo();

            //得到具体的版本号（字符串截取）
            String versionStr = serverinfo.substring(serverinfo.indexOf("/") + 1, serverinfo.indexOf("."));
            //判断服务器版本是否小于8
            if (Integer.parseInt(versionStr)<8) {
                //得到自定义内部类
                HttpServletRequest myRequest = new MyWapper(request);
                //放行资源
                filterChain.doFilter(myRequest,response);
                return;

            }

        }

        //放行资源
        filterChain.doFilter(request, response);
    }

    /**
     * 定义内部类，继承HttpServletRequestWrapper包装类对象，重写getParamter（）方法
     */
    class MyWapper extends HttpServletRequestWrapper{

        private HttpServletRequest request;

        public MyWapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
    }

    /**
     * 重写getparameter方法
     */
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (value == null && !"".equals(value.trim())) {
            try {
                //将默认ISO编码转换为UTF-8
                value = new String(value.getBytes("ISO-8859-1"),"utf-8");
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    @Override
    public void destroy() {

    }
}
